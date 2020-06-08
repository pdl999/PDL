package home.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import home.dao.*;
import home.pojo.Complaint;
import home.pojo.House;
import home.pojo.Judgement;
import home.pojo.User;
import home.services.RenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class RenterServicesImpl implements RenterServices {

    @Autowired
    private RenterMapper renterMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JudgementMapper judgementMapper;
    @Autowired
    private ComplainMapper complainMapper;


    //    用户登录，匹配用户名和密码
    @Override
    public User renterLogin(String uname, String upassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",uname);
        wrapper.eq("pwd",upassword);
        wrapper.eq("isowner","房客");//为了防止房东房客重名，要检查身份
        User user = userMapper.selectOne(wrapper);
        return userMapper.selectOne(wrapper);
    }

    //    租户注册
    @Override
    public int renterReg(User user) {
        int i = userMapper.insert(user);
        return i;
    }



    //    房东登录
    @Override
    public User hostLogin(String uname, String upassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",uname);
        wrapper.eq("pwd",upassword);
        wrapper.eq("isowner","房东");//为了防止房东房客重名，要检查身份
        User user = userMapper.selectOne(wrapper);
        return userMapper.selectOne(wrapper);
    }

    //    房东注册
    @Override
    public int hostReg(User user) {
        int i = userMapper.insert(user);
        return i;
    }

    //    房源推荐，可以在xml文件中更改检索规则
    @Override
    public List<House> houseRecomm() {
        List<House> houses = renterMapper.houseRecomm();
        return houses;
    }

    //    最新上架的展示,使用mybatisplus完成降序查询和分页功能
    @Override
    public List<House> newHouse(int startpage,int pagesize) {
        QueryWrapper<House> wrapper = new QueryWrapper();
        wrapper.orderByDesc("uploadTime");
        wrapper.eq("status","招租");
        wrapper.eq("adminaudit","审核通过");
        Page<House> page = new Page<>(startpage,pagesize);
        IPage<House> houseIPage = houseMapper.selectPage(page, wrapper);
        return houseIPage.getRecords();
    }

    //    控制下面的翻页按钮和页码显示
    @Override
    public List<String> turnPage(int startpage, int pagesize) {
        List<String> list = new ArrayList<>();

        QueryWrapper<House> wrapper = new QueryWrapper();
        wrapper.orderByDesc("uploadTime");
        wrapper.eq("status","招租");
        wrapper.eq("adminaudit","审核通过");
        Page<House> page = new Page<>(startpage,pagesize);
        IPage<House> houseIPage = houseMapper.selectPage(page, wrapper);

        list.add(String.valueOf(startpage));//当前页
        list.add(String.valueOf(houseIPage.getPages()));//一共有多少页
        return list;
    }


    //实现专属推荐功能(爸爸版)
    @Override
    public List<House> perhouse(int startpage, int pagesize, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user==null){
            return null;
        }else{
            String[] str = user.getTags().split("\\*");
            Page<House> page = new Page<>(startpage,pagesize);
            IPage<House> houseIPage = houseMapper.selectHousePage(page, str);
            return houseIPage.getRecords();
        }
    }


    @Override
    public List<String> turnPage2(int startpage, int pagesize,HttpSession session) {
        List<String> list = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        if (user==null){
            return null;
        }else{
            String[] str = user.getTags().split("\\*");
            Page<House> page = new Page<>(startpage,pagesize);
            IPage<House> houseIPage = houseMapper.selectHousePage(page, str);

            list.add(String.valueOf(startpage));//当前页
            list.add(String.valueOf(houseIPage.getPages()));//一共有多少页
            return list;
        }

    }

    @Override
    public List<House> allHouse(int startpage, int pagesize) {
        System.out.println("所有房源");
        QueryWrapper<House> wrapper = new QueryWrapper();
        wrapper.eq("status","招租");
        wrapper.eq("adminaudit","审核通过");
        Page<House> page = new Page<>(startpage,pagesize);
        IPage<House> houseIPage = houseMapper.selectPage(page, wrapper);
        return houseIPage.getRecords();
    }

    @Override
    public List<String> turnPage3(int startpage, int pagesize) {
        System.out.println("所有房源分页");
        List<String> list = new ArrayList<>();
        QueryWrapper<House> wrapper = new QueryWrapper();
        wrapper.eq("status","招租");
        wrapper.eq("adminaudit","审核通过");
        Page<House> page = new Page<>(startpage,pagesize);
        IPage<House> houseIPage = houseMapper.selectPage(page, wrapper);
        list.add(String.valueOf(startpage));//当前页
        list.add(String.valueOf(houseIPage.getPages()));//一共有多少页
        return list;
    }


    //    查询具体的房源信息
    @Override
    public House detailhouse(int id) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("houseId",id);
        House house = houseMapper.selectOne(wrapper);
        return houseMapper.selectOne(wrapper);
    }
    //    获取房源的评价信息
    @Override
    public List<Judgement> getjudge(int houseId) {
        QueryWrapper<Judgement> wrapper = new QueryWrapper<>();
        wrapper.eq("houseId",houseId);
        return judgementMapper.selectList(wrapper);
    }


    //  租房请求的提交
    @Override
    public int renthouse(int houseid, HttpSession session) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("houseId",houseid);
        House house = houseMapper.selectOne(wrapper);
        String status = house.getStatus();
        System.out.println(status);
        if (!status.equals("招租")){
            return 0;
        }else{
            User user = (User) session.getAttribute("user");
            house.setStatus("被租");
            house.setRent(user.getUserId());
            return houseMapper.update(house,wrapper);
        }
    }
    //  提交用户的反馈意见
    @Override
    public int complain(String complaintext,HttpSession session) {
        User user = (User) session.getAttribute("user");
        Complaint complaint = new Complaint();
        complaint.setBelong(user.getUserId());
        complaint.setContent(complaintext);
        complaint.setTime(new Date());
        complaint.setIscheck(0);
        return complainMapper.insert(complaint);
    }


}
