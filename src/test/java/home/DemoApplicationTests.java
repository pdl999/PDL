package home;

import home.dao.houseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    houseMapper houseMapper;

    @Test
    void contextLoads(Model model) {
        Object user = model.getAttribute("user");
        System.out.println(user);

    }

}
