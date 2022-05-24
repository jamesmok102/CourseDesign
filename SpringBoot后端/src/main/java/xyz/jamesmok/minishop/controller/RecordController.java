package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.jamesmok.minishop.service.RecordService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/admin/record/{pageNum}")
    public Map<String, Object> getGoodAll(HttpServletRequest httpServletRequest, @PathVariable int pageNum) {
        String content = "销售员取得操作记录第"+pageNum+"页";
        recordService.insertRecord(httpServletRequest, content);
        Map<String, Object> map = recordService.getAllRecord(pageNum, 10);
        return map;
    }

}
