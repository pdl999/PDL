package home.controller;

import home.pojo.House;
import home.pojo.User;
import home.services.RenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

//处理房东和租户的登录和注册
@Controller
public class LogRegController {

    @Autowired
    private RenterServices renterServices;


//    租客登录
    @RequestMapping("/renter/login")
    public String renterLogin(String renterLogname, String renterLogpwd,HttpSession session,Model model){
        System.out.println("开始执行登录流程+++++++++++++++");
        User user = renterServices.renterLogin(renterLogname,renterLogpwd);
        System.out.println("tip"+session.getAttribute("tip"));
        if (user!=null){
//            查询结果不为空，登录成功
            session.setAttribute("user",user);//存放用户信息
            List<House> houses = renterServices.houseRecomm();
            for (int i=0;i<3;i++){
                String hot = "hot"+i;
                model.addAttribute(hot,houses.get(i));
            }
            return "/main";
        }else{
            model.addAttribute("tip","用户名或密码错误");//存放登录失败提示信息
            return  "/login";
        }
    }

//    租客注册
    @RequestMapping("/renter/reg")
    public String renterReg(@RequestParam(required = true) String regname,
                            @RequestParam(required = true) String regpwd,
                            @RequestParam(required = true) String optionsRadios,
                            @RequestParam(required = true) String[] tags,
                            @RequestParam(required = true) String phone,
                            HttpServletRequest request,Model model){
        tags = request.getParameterValues("tags");//拿到用户的个人标签数组
        String taglist = "";
        for (String tag : tags) { taglist = taglist+tag+"*"; }//将数组转化成字符串
        User user = new User();
        user.setUsername(regname);
        user.setPwd(regpwd);
        user.setPhone(phone);
        user.setSex(optionsRadios);
        user.setTags(taglist);
        user.setPicUrl("http://pig.stadc.cn/back.jpg");
        user.setIsOwner("房客");

        int i = renterServices.renterReg(user);
        if (i==0){
            model.addAttribute("regtip","注册失败，请重新操作");
            return "/login";
        }else{
            model.addAttribute("regtip","注册成功，可以登录");
            return "/login";
        }
    }


//  房东注册
@RequestMapping("/host/reg")
public String hostReg(@RequestParam(required = true) String regname,
                        @RequestParam(required = true) String regpwd,
                        @RequestParam(required = true) String optionsRadios,
                        @RequestParam(required = true) String[] tags,
                        @RequestParam(required = true) String phone,
                        HttpServletRequest request,Model model){
    tags = request.getParameterValues("tags");//拿到房东的个人标签数组
    String taglist = "";
    for (String tag : tags) { taglist = taglist+tag+"*"; }//将数组转化成字符串
    User user = new User();
    user.setUsername(regname);
    user.setPwd(regpwd);
    user.setPhone(phone);
    user.setSex(optionsRadios);
    user.setTags(taglist);
    user.setPicUrl("http://pig.stadc.cn/back.jpg");
    user.setIsOwner("房东");

    int i = renterServices.hostReg(user);
    if (i==0){
        model.addAttribute("regtip","注册失败，请重新操作");
        return "/login";
    }else{
        model.addAttribute("regtip","注册成功，可以登录");
        return "/login";
    }
}

// 管理员登录
    @RequestMapping("/host/login")
    public String hostLogin(String renterLogname, String renterLogpwd, Model model){
        User user = renterServices.hostLogin(renterLogname,renterLogpwd);
        System.out.println(user);
        if (user!=null){
//            查询结果不为空，登录成功
            model.addAttribute("user",user);//存放用户信息
            model.addAttribute("tip","");
            return "redirect:/host.html";
        }else{
            model.addAttribute("tip","用户名或密码错误");//存放登录失败提示信息
            return "/login";
        }
    }


    //    初始化main页面中热推户型的操作，可以在xml中更改推荐房源的检索规则
    @RequestMapping("/houselist")
    public String houseRecomm(Model model){
        List<House> houses = renterServices.houseRecomm();
        for (int i=0;i<3;i++){
            String hot = "hot"+i;
            model.addAttribute(hot,houses.get(i));
        }
        return "main.html";
    }


    //    加载main页面中最新上架的房源信息，通过将房源时间降序排序
    @RequestMapping("/newhouse")
    @ResponseBody
    public List<House> newHouse(int startpage,int pagesize){
        System.out.println("刷新房源信息");
        return renterServices.newHouse(startpage,pagesize);
    }
//    控制显示页面的时候，下方的页数显示
    @RequestMapping("/turnpage")
    @ResponseBody
    public List<String> turnPage(int startpage,int pagesize,Model model){
        System.out.println("正在查询分页");
        System.out.println("tip+"+model.getAttribute("tip"));
        System.out.println("user+"+model.getAttribute("user"));
        return renterServices.turnPage(startpage, pagesize);
    }

//    跳转到具体的房源信息展示界面
    @RequestMapping("/housedetail")
    public String housedetail(int id,Model model){
        model.addAttribute("detail",renterServices.detailhouse(id));//房源信息
        model.addAttribute("judgelist",renterServices.getjudge(id));//评价信息
        return "/detail.html";
    }










}
