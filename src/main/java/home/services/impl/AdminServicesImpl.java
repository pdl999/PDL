package home.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import home.dao.AdminMapper;
import home.dao.ComplainMapper;
import home.dao.HouseMapper;
import home.dao.UserMapper;
import home.pojo.Admin;
import home.pojo.Complaint;
import home.pojo.House;
import home.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServicesImpl implements AdminServices {

    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    HouseMapper houseMapper;
    @Autowired
    ComplainMapper complainMapper;
//管理员登录
    @Override
    public Admin adminLogin(String adminname, String adminpwd) {
        return adminMapper.adminLogin(adminname, adminpwd);
    }
    //查看所有未通过审核的房源信息
    @Override
    public List<House> adminAllHouse(int startpage, int pagesize) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("adminaudit","等待审核");
        Page<House> page = new Page<>(startpage,pagesize);
        IPage<House> houseIPage = houseMapper.selectPage(page, wrapper);
        return houseIPage.getRecords();
    }

    @Override
    public List<String> auditPage(int startpage, int pagesize){
        List<String> list = new ArrayList<>();
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("adminaudit","等待审核");
        Page<House> page = new Page<>(startpage,pagesize);
        IPage<House> houseIPage = houseMapper.selectPage(page, wrapper);
        list.add(String.valueOf(startpage));
        list.add(String.valueOf(houseIPage.getPages()));
        return list;
    }


    @Override
    public int updatehouse(String audit,int houseid) {
        int updatehouse = adminMapper.updatehouse(audit, houseid);
        System.out.println(updatehouse+"更改状态++++++++++++++++");
        return updatehouse;
    }


//    查找没有处理过的反馈意见
    @Override
    public List<Complaint> nocheckComp(int startpage, int pagesize) {
        QueryWrapper<Complaint> wrapper = new QueryWrapper<>();
        wrapper.eq("ischeck","0");
        Page<Complaint> page = new Page<>(startpage,pagesize);
        IPage<Complaint> complaintIPage = complainMapper.selectPage(page, wrapper);
        return complaintIPage.getRecords();
    }
//意见信息分页
    @Override
    public List<String> compPage(int startpage, int pagesize) {
        List<String> list = new ArrayList<>();
        QueryWrapper<Complaint> wrapper = new QueryWrapper<>();
        wrapper.eq("ischeck","0");
        Page<Complaint> page = new Page<>(startpage,pagesize);
        IPage<Complaint> houseIPage = complainMapper.selectPage(page, wrapper);
        list.add(String.valueOf(startpage));
        list.add(String.valueOf(houseIPage.getPages()));
        return list;
    }
// 处理意见
    @Override
    public int docomp(int compid) {
        return adminMapper.docomp(compid);
    }


}
