package home.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")//设置对象名和表明对应
public class User {
    private Integer userId;
    private String username;
    private String pwd;
    private String isOwner;//是不是房东
    private String  tags;//个性标签
    private String phone;
    private String sex;
    private String picUrl;
}
