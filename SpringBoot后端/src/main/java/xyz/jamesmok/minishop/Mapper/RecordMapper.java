package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jamesmok.minishop.entiiy.Record;

@Mapper
public interface RecordMapper {

    @Insert("insert into record(name, content, time, ip) values(#{name}, #{content}, #{time}, #{ip})")
    int insertRecord(Record record);

    @Select("select * from record")
    Page<Record> getAllRecord();

}
