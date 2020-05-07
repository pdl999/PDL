package home.services;

import home.pojo.User;
import home.pojo.house;
import home.pojo.judgement;
import org.springframework.ui.Model;

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
    //    main页面的专属推荐
    List<house> perhouse(int startpage, int pagesize, Model model);





//    根据id查询具体的房源信息进行展示
    house detailhouse(int id);
//    根据房源id查询房源的评价信息
    List<judgement> getjudge(int houseid);
}
