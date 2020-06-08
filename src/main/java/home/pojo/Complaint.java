package home.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("complaint")//设置对象名和表明对应
public class Complaint {

    @TableId(type = IdType.AUTO)
    private  int complaintId;
    private  int belong;
    private  String content;
    private  Date time;
    private  int ischeck;
}
