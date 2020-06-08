package home.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Renter {
    private Integer rentId;
    private String username;
    private String phone;
    private String sex;
    private String picUrl;
    public String houseName;
    private Date uploadTime;
}
