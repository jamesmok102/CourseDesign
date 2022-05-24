package xyz.jamesmok.minishop;

import com.github.pagehelper.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.jamesmok.minishop.Mapper.SalerMapper;
import xyz.jamesmok.minishop.entiiy.SalerEntity;
import xyz.jamesmok.minishop.service.SalerService;

import java.util.Map;

@SpringBootTest
class MinishopApplicationTests {

    @Autowired
    private SalerMapper salerMapper;

    @Autowired
    private SalerService salerService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAll() {
        Page<SalerEntity> users = salerMapper.getSalerAll();
        System.out.println(users.toString());
    }

    @Test
    void testServiceGetAll() {
        Map<String, Object> result = salerService.getSalerAll(1, 3);
        System.out.println(result.get("total"));
        System.out.println(result.get("data"));
    }

}
