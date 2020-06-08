package home.utils;

import lombok.Data;

import java.util.List;

/**
 * 返回结果分页类
 */
@Data
public class PageResult<E> {
    private Long total;
    private List<E> list;


    public PageResult() {
    }

    public PageResult(Long total, List<E> list) {
        this.total = total;
        this.list = list;
    }
}
