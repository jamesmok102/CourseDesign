package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jamesmok.minishop.entiiy.BrowseRecordEntity;

@Mapper
public interface BrowseRecordMapper {

    @Insert("insert into browselog(user_id,saler_id,good_id,browse) values(#{user_id},#{saler_id},#{good_id},#{browse})")
    int insertBrowseRecord(BrowseRecordEntity browseRecordEntity);

    @Select("select b.id,s.name salername,u.name username,g.name goodname,b.browse from browselog b,salers s,user u,good g where u.id = #{id} and b.saler_id=s.id and b.user_id=u.id and b.good_id=g.id")
    Page<BrowseRecordEntity> getBrowseRecordByUserId(long id);

}
