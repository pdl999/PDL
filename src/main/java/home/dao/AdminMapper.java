package home.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import home.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

//    管理员登录
    Admin adminLogin(String adminname, String adminpwd);

    int updatehouse(String audit, int houseid);

    //处理反馈意见
    int docomp(int compid);

}
