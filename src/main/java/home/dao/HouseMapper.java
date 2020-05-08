package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import home.pojo.House;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface HouseMapper extends BaseMapper<House> {

}
