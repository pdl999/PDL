package home.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import home.dao.HouseMapper;
import home.dao.judgementMapper;
import home.pojo.User;
import home.pojo.judgement;
import home.dao.RenterMapper;
import home.pojo.House;
import home.services.RenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service

public class RenterServicesImpl implements RenterServices {

    @Autowired
    private RenterMapper renterMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private judgementMapper judgementMapper;


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
        Page<House> page = new Page<>(startpage,pagesize);
        IPage<House> houseIPage = houseMapper.selectPage(page, wrapper);
        list.add(String.valueOf(page.hasPrevious()));//是否有上一页
        list.add(String.valueOf(page.hasNext()));//是否有下一页
        list.add(String.valueOf(startpage));//当前页
        list.add(String.valueOf(houseIPage.getPages()));//一共有多少页
        return list;
    }

    @Override
    public List<House> perhouse(int startpage, int pagesize, Model model){
        Object user = model.getAttribute("user");
//        未完成，等待用户数据
        return new ArrayList<>();

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
    public List<judgement> getjudge(int houseid) {
        QueryWrapper<judgement> wrapper = new QueryWrapper<>();
        wrapper.eq("houseid",houseid);
        return judgementMapper.selectList(wrapper);
    }


}
