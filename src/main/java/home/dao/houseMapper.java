package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import home.pojo.house;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface houseMapper extends BaseMapper<house> {

}
