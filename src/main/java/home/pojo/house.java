package home.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class house {

    private int houseid;
    private  String housename;
    private  String houseadder;
    private  double pirce;
    private  int owner;//房东id
    private  int rent;//租户id
    private  int version;//乐观锁操作
    private  String details;//房源的详细介绍
    private  String tagslist;//房源的所属标签
    private  Date uploadtime;//房源的初次上传时间
    private  Date previoustime;//最近一次被租出去的时间
    private  Date lowertime;//下架时间
    private String status;//房源的状态
    private String housepic;//图片路径


}
