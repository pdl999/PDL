package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import home.pojo.Judgement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JudgementMapper extends BaseMapper<Judgement> {
}
