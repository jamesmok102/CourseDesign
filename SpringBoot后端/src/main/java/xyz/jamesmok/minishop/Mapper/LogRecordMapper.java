package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jamesmok.minishop.entiiy.LogRecordEntity;

@Mapper
public interface LogRecordMapper {

    @Insert("insert into log_record(state, role, time, ip, name) values(#{state}, #{role}, #{time}, #{ip}, #{name})")
    int insertLogRecord(LogRecordEntity logRecordEntity);

    @Select("select * from log_record")
    Page<LogRecordEntity> getLogRecordAll();

}
