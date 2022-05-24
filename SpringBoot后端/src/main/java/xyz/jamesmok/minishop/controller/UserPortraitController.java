package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.jamesmok.minishop.service.RecordService;
import xyz.jamesmok.minishop.service.UserPortraitService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserPortraitController {

    @Autowired
    private UserPortraitService userPortraitService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/admin/userportrait/{pageNum}")
    public Map<String, Object> check(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "管理员取得用户画像第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = userPortraitService.check(pageNum);
        return map;
    }

}
