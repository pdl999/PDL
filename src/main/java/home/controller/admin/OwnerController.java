package home.controller.admin;

import home.pojo.House;
import home.pojo.Judgement;
import home.pojo.Renter;
import home.pojo.User;
import home.services.OwnerServices;
import home.services.RenterServices;
import home.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OwnerController {
    @Autowired
    private OwnerServices ownerServices;
    @Autowired
    private RenterServices renterServices;
    @Autowired
    private UserServices userServices;

    public OwnerController() {
    }

    @RequestMapping({"/owner/show"})
    @ResponseBody
    public List<House> ownerShow(int startpage, int pagesize, HttpSession session) {
        User user = (User)session.getAttribute("user");
        return this.ownerServices.showOwnerList(startpage, pagesize, session);
    }

    @RequestMapping({"/owner/beizu"})
    @ResponseBody
    public List<House> ownerShowBeiZu(int startpage, int pagesize, HttpSession session, Model model) {
        return this.ownerServices.showStatusList(startpage, pagesize, session, "被租");
    }

    @RequestMapping({"/owner/dengdai"})
    @ResponseBody
    public List<House> ownerShowDengDai(int startpage, int pagesize, HttpSession session, Model model) {
        return this.ownerServices.showStatusList(startpage, pagesize, session, "等待");
    }

    @RequestMapping({"/owner/xiajia"})
    @ResponseBody
    public List<House> ownerShowXiaJia(int startpage, int pagesize, HttpSession session, Model model) {
        return this.ownerServices.showStatusList(startpage, pagesize, session, "下架");
    }

    @RequestMapping({"/owner/zhaozu"})
    @ResponseBody
    public List<House> ownerShowZhaoZu(int startpage, int pagesize, HttpSession session, Model model) {
        return this.ownerServices.showStatusList(startpage, pagesize, session, "招租");
    }

    @RequestMapping({"/owner/showrenter"})
    @ResponseBody
    public List<Renter> ownerShowRenter(int startpage, int pagesize, HttpSession session, Model model) {
        return this.ownerServices.showRenterList(startpage, pagesize, session);
    }

    @RequestMapping({"/owner/showMessage"})
    @ResponseBody
    public List<Judgement> ownerShowMessage(int startpage, int pagesize, HttpSession session, Model model) {
        return this.ownerServices.showMessage(startpage, pagesize, session);
    }

    @RequestMapping({"/owner/ownerhouse"})
    public String ownerHouseDetail(Integer houseId, Model model, HttpSession session) {
        model.addAttribute("ownerdetail", this.ownerServices.showOwnerHouseDetail(houseId));
        return "/test/ownerhouse.html";
    }

    @RequestMapping({"/owner/xiajiahouse"})
    public String ownerHouseXiaJia(Integer houseId, Model model, HttpSession session) {
        model.addAttribute("ownerdetail", this.ownerServices.xiaJiaOwnerHouseDetail(houseId));
        return "/test/ownerhouse.html";
    }

    @RequestMapping({"/owner/xiugai"})
    public String ownerHouseXiuGai(Integer houseId, Model model, HttpSession session) {
        model.addAttribute("ownerdetail", this.ownerServices.showOwnerHouseDetail(houseId));
        return "/test/pages/xiugai.html";
    }

    @RequestMapping({"/owner/modifyHouse"})
    public String ownerHouseModify(Integer houseId, String houseAdder, String houseName, String pirce, String tagsList, String details, Model model, HttpSession session) {
        model.addAttribute("ownerdetail", this.ownerServices.modifyHouse(houseId, houseAdder, houseName, pirce, tagsList, details));
        return "/test/ownerhouse.html";
    }

    @RequestMapping({"/owner/addHouse"})
    public String ownerHouseAdd(String houseAdder, String houseName, String pirce, String tagsList, String details, Model model, HttpSession session) {
        model.addAttribute("ownerdetail", this.ownerServices.addHouse(session, houseAdder, houseName, pirce, tagsList, details));
        return "/test/ownerhouse.html";
    }
}