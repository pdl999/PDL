package home.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import home.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper  extends BaseMapper<User> {
    User loginId(String userId, String password);
    User findUserById(String userId);
    boolean modifyUser(String userId,String userName,String password,String userPic,String signature);



}
