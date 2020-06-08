package home.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * 返回结果类
 */
@Data
@JsonInclude(Include.NON_NULL)
public class Result {
    private boolean status;
    private Object data;

    public Result(){

    }

    public Result(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }
}
