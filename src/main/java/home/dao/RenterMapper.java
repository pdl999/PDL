package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import home.pojo.House;
import home.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RenterMapper extends BaseMapper<User> {

//    main页面的热推户型
    List<House> houseRecomm();
}
