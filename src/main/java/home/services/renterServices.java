package home.services;

import home.pojo.User;
import home.pojo.house;

import java.util.List;

public interface renterServices {
//    租客登录
    public User renterLogin(String uname, String upassword);
//    租客注册
    public  int renterReg(User user);
//    租客登录
    public User hostLogin(String uname, String upassword);
//    房东注册
    public int hostReg(User user);
//    mian页面的热推户型
    List<house> houseRecomm();
//    main页面的最新上架
    List<house> newHouse(int startpage,int pagesize);
//    控制分页按钮和页码显示
    List<String> turnPage(int startpage,int pagesize);
}
