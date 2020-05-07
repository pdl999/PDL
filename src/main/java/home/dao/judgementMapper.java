package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import home.pojo.judgement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface judgementMapper extends BaseMapper<judgement> {
}
