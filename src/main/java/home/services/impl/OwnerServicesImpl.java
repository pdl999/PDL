package home.services.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import home.dao.HouseMapper;
import home.dao.RenterMapper;
import home.pojo.House;
import home.pojo.Judgement;
import home.pojo.Renter;
import home.pojo.User;
import home.services.OwnerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServicesImpl implements OwnerServices {
    @Autowired
    private RenterMapper renterMapper;
    @Autowired
    private HouseMapper houseMapper;
    User user;
    Integer userId;

    public OwnerServicesImpl() {
    }

    public List<House> showOwnerList(int startpage, int pagesize, HttpSession session) {
        this.user = (User)session.getAttribute("user");
        this.userId = this.user.getUserId();
        Page<House> page = new Page((long)startpage, (long)pagesize);
        IPage<House> houseIPage = this.houseMapper.selectOwnerHouse(page, this.userId);
        System.out.println("纯房东房屋list" + houseIPage.getRecords().size());
        return houseIPage.getRecords();
    }

    public List<House> showStatusList(int startpage, int pagesize, HttpSession session, String status) {
        this.user = (User)session.getAttribute("user");
        this.userId = this.user.getUserId();
        Page<House> page = new Page((long)startpage, (long)pagesize);
        IPage<House> houseIPage = this.houseMapper.showStatusList(page, this.userId, status);
        System.out.println(status + "不同状态下的房屋信息" + houseIPage.getRecords().size());
        return houseIPage.getRecords();
    }

    public List<Renter> showRenterList(int startpage, int pagesize, HttpSession session) {
        Page<Renter> page = new Page((long)startpage, (long)pagesize);
        IPage<Renter> RenterIPage = this.renterMapper.showRenterList(page, this.userId);
        System.out.println(RenterIPage.getRecords().size());
        return RenterIPage.getRecords();
    }

    public List<Judgement> showMessage(int startpage, int pagesize, HttpSession session) {
        this.user = (User)session.getAttribute("user");
        this.userId = this.user.getUserId();
        Page<Judgement> page = new Page((long)startpage, (long)pagesize);
        IPage<Judgement> JudgementIPage = this.renterMapper.showMessage(page, this.userId);
        System.out.println(JudgementIPage.getRecords().size());
        return JudgementIPage.getRecords();
    }

    public House showOwnerHouseDetail(Integer houseId) {
        QueryWrapper<House> wrapper = new QueryWrapper();
        wrapper.eq("houseId", houseId);
        return changeTags(this.houseMapper.selectOne(wrapper));
    }

    public House xiaJiaOwnerHouseDetail(Integer houseId) {
        if (this.renterMapper.xiaJiaOwnerHouseDetail(houseId)) {
            QueryWrapper<House> wrapper = new QueryWrapper();
            wrapper.eq("houseId", houseId);
            return changeTags(this.houseMapper.selectOne(wrapper));
        } else {
            return null;
        }
    }

    @Override
    public House uploadHouse(Integer houseId) {
        if (this.renterMapper.uploadHouse(houseId)) {
            QueryWrapper<House> wrapper = new QueryWrapper();
            wrapper.eq("houseId", houseId);
            return changeTags(this.houseMapper.selectOne(wrapper));
        } else {
            return null;
        }
    }

    public House modifyHouse(Integer houseId, String houseAdder, String houseName, String pirce, String tagsList, String details) {
        if (this.renterMapper.modifyHouse(houseId, houseAdder, houseName, pirce, tagsList, details)) {
            QueryWrapper<House> wrapper = new QueryWrapper();
            wrapper.eq("houseId", houseId);
            return changeTags(this.houseMapper.selectOne(wrapper));
        } else {
            return null;
        }
    }

    public House addHouse(HttpSession session, String houseAdder, String houseName, String pirce, String tagsList, String details) {
        this.user = (User)session.getAttribute("user");
        this.userId = this.user.getUserId();
        Integer houseId = this.renterMapper.selectLastOneHouseId();
        if (this.renterMapper.addHouse(houseAdder, houseName, pirce, tagsList, details, this.userId)) {
            QueryWrapper<House> wrapper = new QueryWrapper();
            wrapper.eq("houseId", houseId);
            return changeTags(this.houseMapper.selectOne(wrapper));
        } else {
            return null;
        }
    }

    @Override
    public List<String> turnpageShow(int startpage, int pagesize, HttpSession session) {
        List<String> list = new ArrayList<>();
        this.user = (User)session.getAttribute("user");
        Integer userId = this.user.getUserId();
        Page<House> page = new Page((long)startpage, (long)pagesize);
        IPage<House> houseIPage = this.houseMapper.selectOwnerHouse(page, userId);
        System.out.println("纯房东房屋list" + houseIPage.getRecords().size());
        list.add(String.valueOf(startpage));//当前页
        list.add(String.valueOf(houseIPage.getPages()));//一共有多少页
        return list;
    }

    @Override
    public List<String> turnpageZhaoZu(int startpage, int pagesize, HttpSession session) {
        return turnPageAll(startpage, pagesize, session,"招租");
    }

    @Override
    public List<String> turnpageBeiZu(int startpage, int pagesize, HttpSession session) {
        return turnPageAll(startpage, pagesize, session,"被租");
    }

    @Override
    public List<String> turnpageDengDai(int startpage, int pagesize, HttpSession session) {
        return turnPageAll(startpage, pagesize, session,"等待");
    }

    @Override
    public List<String> turnpageXiaJia(int startpage, int pagesize, HttpSession session) {
        return turnPageAll(startpage, pagesize, session,"下架");
    }

    public House changeTags(House house){
        String tagsListArray[]=house.getTagsList().split("\\*");
        house.setTagsListArray(tagsListArray);
        return house;
    }

    public List<String> turnPageAll(int startpage, int pagesize, HttpSession session,String status ){
        List<String> list = new ArrayList<>();
        User user = (User)session.getAttribute("user");
        Integer userId = user.getUserId();
        Page<House> page = new Page((long)startpage, (long)pagesize);
        IPage<House> houseIPage = houseMapper.showStatusList(page, userId, status);
        list.add(String.valueOf(startpage));//当前页
        list.add(String.valueOf(houseIPage.getPages()));//一共有多少页
        return list;
    }

}
