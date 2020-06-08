package home.services;

import home.pojo.Admin;
import home.pojo.Complaint;
import home.pojo.House;

import java.util.List;

public interface AdminServices {

//    管理员登录
Admin adminLogin(String adminname, String adminpwd);

//查看所有等待审核房源信息
List<House> adminAllHouse(int startpage, int pagesize);

//房源页码信息
List<String> auditPage(int startpage, int pagesize);

//更改房源状态
int updatehouse(String audit, int houseid);


//查看所有反馈信息
List<Complaint> nocheckComp(int startpage, int pagesize);

//反馈页码信息
List<String> compPage(int startpage, int pagesize);

//处理反馈意见
int docomp(int compid);

}
