package home.services;

import home.pojo.House;
import home.pojo.Judgement;
import home.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RenterServices {


    //    租客登录
    public User renterLogin(String uname, String upassword);
    //    租客注册
    public  int renterReg(User user);
    //    租客登录
    public User hostLogin(String uname, String upassword);
    //    房东注册
    public int hostReg(User user);
    //    mian页面的热推户型
    List<House> houseRecomm();
    //    main页面的最新上架
    List<House> newHouse(int startpage,int pagesize);
    List<String> turnPage(int startpage,int pagesize);
    //    main页面的专属推荐
    List<House> perhouse(int startpage, int pagesize, HttpSession session);
    List<String> turnPage2(int startpage, int pagesize, HttpSession session);
    //    main页面的所有房源
    List<House> allHouse(int startpage, int pagesize);
    List<String> turnPage3(int startpage, int pagesize);
    //    根据id查询具体的房源信息进行展示
    House detailhouse(int id);
    //    根据房源id查询房源的评价信息
    List<Judgement> getjudge(int houseId);
    //    租房操作
    int renthouse(int houseid,HttpSession session);
    //    提交意见反馈
    int complain(String complaintext,HttpSession session);
}
