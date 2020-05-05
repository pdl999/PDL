package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import home.pojo.User;
import home.pojo.house;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface renterMapper extends BaseMapper<User> {

//    main页面的热推户型
    List<house> houseRecomm();
}
