package home.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import home.dao.HouseMapper;
import home.dao.JudgementMapper;
import home.dao.RenterMapper;
import home.pojo.House;
import home.pojo.Judgement;
import home.pojo.User;
import home.services.RenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service

public class RenterServicesImpl implements RenterServices {

    @Autowired
    private RenterMapper renterMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private JudgementMapper judgementMapper;


//    用户登录，匹配用户名和密码
    @Override
    public User renterLogin(String uname, String upassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",uname);
        wrapper.eq("pwd",upassword);
        wrapper.eq("isowner","房客");//为了防止房东房客重名，要检查身份
        User user = renterMapper.selectOne(wrapper);
        return renterMapper.selectOne(wrapper);
    }

//    租户注册
    @Override
    public int renterReg(User user) {
        int i = renterMapper.insert(user);
        return i;
    }



//    房东登录
    @Override
    public User hostLogin(String uname, String upassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",uname);
        wrapper.eq("pwd",upassword);
        wrapper.eq("isowner","房东");//为了防止房东房客重名，要检查身份
        User user = renterMapper.selectOne(wrapper);
        return renterMapper.selectOne(wrapper);
    }

    //    房东注册
    @Override
    public int hostReg(User user) {
        int i = renterMapper.insert(user);
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
//实现专属推荐功能
    @Override
    public List<House> perhouse(int startpage, int pagesize, HttpSession session){
        List<House> houseslist = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        String[] str = user.getTags().split("\\*");
        for (String s : str) {
            QueryWrapper<House> wrapper = new QueryWrapper();
            wrapper.eq("status","招租");
            wrapper.like("tagsList",s);
            Page<House> page = new Page<>(startpage,pagesize);

            for (House house : houseMapper.selectList(wrapper)) {
                if (houseslist.contains(house)){}//重复的数据不会再次添加
                else{houseslist.add(house);}
            }
        }
        return houseslist;
    }



    //    控制下面的翻页按钮和页码显示
    @Override
    public List<String> turnPage2(int startpage, int pagesize,HttpSession session) {
        List<String> list = new ArrayList<>();
        List<House> houseslist = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        String[] str = user.getTags().split("\\*");
        for (String s : str) {
            QueryWrapper<House> wrapper = new QueryWrapper();
            wrapper.eq("status","招租");
            wrapper.like("tagsList",s);
            Page<House> page = new Page<>(startpage,pagesize);
            for (House house : houseMapper.selectPage(page, wrapper).getRecords()) {
                if (houseslist.contains(house)){}//重复的数据不会再次添加
                else{houseslist.add(house);}
            }
        }
        list.add(String.valueOf(startpage));//当前页
        int pagetol = 0;
        if (houseslist.size()%pagesize>0){
            list.add(String.valueOf(houseslist.size()/pagesize+1));//一共有多少页
        }else{
            list.add(String.valueOf(houseslist.size()));
        }
        return list;
    }











}
