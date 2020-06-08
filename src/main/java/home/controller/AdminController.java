package home.controller;

import home.pojo.Admin;
import home.pojo.Complaint;
import home.pojo.House;
import home.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminServices adminServices;

//    管理员登录
    @RequestMapping("/adminLogin")
    public String adminLogin(String username, String password, Model model, HttpSession session){
        Admin admin = adminServices.adminLogin(username, password);
        if (admin == null){
            model.addAttribute("adminLogTip","用户名或密码错误");
            return "/adm/adminLogin";
        }else{
            model.addAttribute("adminLogTip","");
            session.setAttribute("admin",admin);
//            重定向跳转会使得原有的视图解析器失效，需要自己重新定义
            return "redirect:/admin/index";
        }
    }


//    管理员注销登录
    @RequestMapping("/admin/logout")
    public String adminLogout(HttpSession session){
        session.invalidate();
        return "adm/adminLogin";
    }

//    查询所有没有通过审核的房源信息
    @RequestMapping("/admin/allhouseAduit")
    public String adminAllHouse(@RequestParam(value="startpage",required=false,defaultValue = "1")int startpage,
                                @RequestParam(value="pagesize",required=false,defaultValue = "5")int pagesize, Model model){
        List<House> houseList = adminServices.adminAllHouse(startpage, pagesize);//房源数据
        List<String> list = adminServices.auditPage(startpage, pagesize);//页码数据
        model.addAttribute("houseList",houseList);
        model.addAttribute("nowpage",list.get(0));
        model.addAttribute("tolpage",list.get(1));
        return "adm/houseAudit";
    }

    @RequestMapping("/admin/audit")
    public String audit(@RequestParam(value="startpage",required=false,defaultValue = "1")int startpage,
                        @RequestParam(value="pagesize",required=false,defaultValue = "5")int pagesize,
                        String audit,int houseid,Model model){
        adminServices.updatehouse(audit,houseid);
        List<House> houseList = adminServices.adminAllHouse(startpage, pagesize);//房源数据
        List<String> list = adminServices.auditPage(startpage, pagesize);//页码数据
        model.addAttribute("houseList",houseList);
        model.addAttribute("nowpage",list.get(0));
        model.addAttribute("tolpage",list.get(1));
        return "adm/houseAudit";
    }


    @RequestMapping("/admin/usrecomplaint")
    public String usrecomplaint(@RequestParam(value="startpage",required=false,defaultValue = "1")int startpage,
                                @RequestParam(value="pagesize",required=false,defaultValue = "5")int pagesize, Model model){
        List<Complaint> complaints = adminServices.nocheckComp(startpage, pagesize);
        List<String> list = adminServices.compPage(startpage, pagesize);
        model.addAttribute("compmes",complaints);
        model.addAttribute("compnowpage",list.get(0));
        model.addAttribute("comptolpage",list.get(1));
        return  "/adm/usrecomplaint";
    }

    @RequestMapping("/admin/docomp")
    public String  docomp(@RequestParam(value="startpage",required=false,defaultValue = "1")int startpage,
                          @RequestParam(value="pagesize",required=false,defaultValue = "5")int pagesize, Model model
                          ,int compid){
        adminServices.docomp(compid);
        List<Complaint> complaints = adminServices.nocheckComp(startpage, pagesize);
        List<String> list = adminServices.compPage(startpage, pagesize);
        model.addAttribute("compmes",complaints);
        model.addAttribute("compnowpage",list.get(0));
        model.addAttribute("comptolpage",list.get(1));
        return  "/adm/usrecomplaint";
    }


}
