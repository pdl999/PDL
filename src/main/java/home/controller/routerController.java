package home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//最基本的页面跳转
@Controller
public class routerController {

    @RequestMapping({"/","/index","index.html"})
    public String toIndex(Model model){
        return "index";
    }

    @RequestMapping({"/main","/main.html"})
    public String toMain(){
        return "main";
    }

    @RequestMapping({"/userback","userback.html"})
    public String toFeedback(){
        return "userback";
    }

    @RequestMapping({"/login","login.html"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping({"/personal","personal.html"})
    public String toPersonal(){
        return "personal";
    }

    @RequestMapping("/detail.html")
    public String toDetail(){
        return "detail";
    }

    @RequestMapping("/host.html")
    public String toHost(){
        return "host";
    }
}
