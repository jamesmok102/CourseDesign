package xyz.jamesmok.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.jamesmok.minishop.entiiy.BrowseRecordEntity;
import xyz.jamesmok.minishop.service.BrowseRecordService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class BrowseRecordController {

    @Autowired
    private BrowseRecordService browseRecordService;

    @PostMapping ("/userislogin/browserecord")
    public Map<String, Object> insertBrowseRecord(@RequestBody BrowseRecordEntity browseRecordEntity) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", browseRecordService.insertBrowseRecord(browseRecordEntity));
        return map;
    }

    @GetMapping("/saler/browse/{id}/{pageNum}")
    public Map<String, Object> getBrowseRecordByUserId(@PathVariable long id, @PathVariable int pageNum) {
        Map<String, Object> map = browseRecordService.getBrowseRecordByUserId(pageNum, 5, id);
        return map;
    }


}
