package xyz.jamesmok.minishop.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface RecordService {

    int insertRecord(HttpServletRequest httpServletRequest, String content);

    Map<String, Object> getAllRecord(int pageNum, int pageSize);

}
