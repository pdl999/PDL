package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import home.pojo.House;
import home.pojo.Judgement;
import home.pojo.Renter;
import home.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RenterMapper extends BaseMapper<House> {

    List<House> houseRecomm();

    IPage<Renter> showRenterList(Page<Renter> page, Integer userId);

    IPage<Judgement> showMessage(Page<Judgement> page, Integer userId);

    boolean xiaJiaOwnerHouseDetail(Integer houseId);

    boolean modifyHouse(Integer houseId, String houseAdder, String houseName, String pirce, String tagsList, String details);

    boolean addHouse(String houseAdder, String houseName, String pirce, String tagsList, String details, Integer userId);

    Integer selectLastOneHouseId();

    boolean uploadHouse(Integer houseId);
}
