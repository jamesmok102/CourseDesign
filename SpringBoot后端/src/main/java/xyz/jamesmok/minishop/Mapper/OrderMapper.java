package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import xyz.jamesmok.minishop.entiiy.CartEntity;
import xyz.jamesmok.minishop.entiiy.GoodEntity;
import xyz.jamesmok.minishop.entiiy.OrderEntity;

@Mapper
public interface OrderMapper {

    @Select("select orders.id,user.name username,orders.time,orders.address from orders,user where orders.user_id=user.id and user.id=#{id}")
    Page<OrderEntity> getOrderByUserId(long id);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    int deleteOrderById(long id);

    @Delete("<script>DELETE FROM orders WHERE id in <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int batchDeleteOrderById(int[] ids);

    @Insert("insert into orders(user_id, time, address) values(#{user_id}, #{time}, #{address})")
    int insertOrder(OrderEntity orderEntity);

    @Select("SELECT LAST_INSERT_ID()")
    Long getLastId();

}
