package home.services;

import home.pojo.House;
import home.pojo.Judgement;
import home.pojo.Renter;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OwnerServices {
    List<House> showOwnerList(int startpage, int pagesize, HttpSession session);

    List<House> showStatusList(int startpage, int pagesize, HttpSession session, String status);

    List<Renter> showRenterList(int startpage, int pagesize, HttpSession session);

    List<Judgement> showMessage(int startpage, int pagesize, HttpSession session);

    House showOwnerHouseDetail(Integer houseId);

    House xiaJiaOwnerHouseDetail(Integer houseId);

    House uploadHouse(Integer houseId);

    House modifyHouse(Integer houseId, String houseAdder, String houseName, String pirce, String tagList, String details);

    House addHouse(HttpSession session, String houseAdder, String houseName, String pirce, String tagsList, String details);

    List<String> turnpageShow(int startpage, int pagesize, HttpSession session);

    List<String> turnpageZhaoZu(int startpage, int pagesize, HttpSession session);

    List<String> turnpageBeiZu(int startpage, int pagesize, HttpSession session);

    List<String> turnpageDengDai(int startpage, int pagesize, HttpSession session);

    List<String> turnpageXiaJia(int startpage, int pagesize, HttpSession session);



}