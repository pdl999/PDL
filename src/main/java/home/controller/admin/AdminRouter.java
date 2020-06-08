package home.controller.admin;

import home.services.RenterServices;
import home.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminRouter {
    @Autowired
    private RenterServices renterServices;
    @Autowired
    private UserServices userServices;

    public AdminRouter() {
    }

    @RequestMapping({"/test/index", "/test/index.html"})
    public String testIndex(Model model) {
        return "test/index";
    }

    @RequestMapping({"/test/pages/renter", "/test/pages/renter.html"})
    public String testRenter(Model model) {
        return "test/pages/renter";
    }



    @RequestMapping({"/test/pages/mailbox", "/test/pages/mailbox.html"})
    public String testMailbox(Model model) {
        return "test/pages/mailbox";
    }

    @RequestMapping({"/test/pages/addnew", "/test/pages/addnew.html"})
    public String addNew(Model model) {
        return "test/pages/addnew";
    }

    @RequestMapping({"/test/ownerhouse", "/test/ownerhouse.html"})
    public String toOwnerHouseDetail(Model model) {
        return "test/ownerhouse";
    }

    @RequestMapping({"/test/xiugai", "/test/xiugai.html"})
    public String toOwnerHouseXiuGai(Model model) {
        return "test/ownerhouse";
    }
}
