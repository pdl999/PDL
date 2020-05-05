package home;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import home.dao.houseMapper;
import home.pojo.house;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    houseMapper houseMapper;

    @Test
    void contextLoads() {
        int i = 1;
        int u = 6;
        Page<house> page = new Page<>(i,u);
        IPage<house> houseIPage = houseMapper.selectPage(page, null);
//        for (house record : page.getRecords()) {
//            System.out.println(record);
//        }
        System.out.println("记录总条数"+houseIPage.getTotal());
        System.out.println("一共有几页"+houseIPage.getPages());
        System.out.println(houseIPage.getCurrent());
        System.out.println("有没有下一页"+page.hasNext());
        System.out.println("有没有上一页"+page.hasPrevious());


    }

}
