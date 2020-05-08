package home.pojo;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("house")//设置对象名和表明对应
public class House {

    private int houseId;
    public  String houseName;
    private  String houseAdder;
    private  double pirce;
    private  int owner;//房东id
    private  int rent;//租户id
    private  int version;//乐观锁操作
    private  String details;//房源的详细介绍
    private  String tagsList;//房源的所属标签
    private  Date uploadTime;//房源的初次上传时间
    private  Date previousTime;//最近一次被租出去的时间
    private  Date lowerTime;//下架时间
    private String status;//房源的状态
    private String housePic;//图片路径


}
