package home.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userid;
    private String username;
    private String pwd;
    private String isowner;//是不是房东
    private String  tags;//个性标签
    private String phone;
    private String sex;
    private String picurl;
}
