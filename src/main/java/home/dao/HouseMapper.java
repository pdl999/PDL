package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import home.pojo.House;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface HouseMapper extends BaseMapper<House> {

    //    专属推荐
    IPage<House> selectHousePage(Page<House> page, String[] tagsList);

    IPage<House> selectOwnerHouse(Page<House> page, Integer userId);

    IPage<House> showStatusList(Page<House> page, Integer userId, String status);
}