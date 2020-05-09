package home.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Judgement{
    private int jId;
    private String jcontent;
    private Date jtime;
    private int houseId;
}
