package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jamesmok.minishop.entiiy.CartEntity;
import xyz.jamesmok.minishop.entiiy.OrderDetailEntity;

@Mapper
public interface OrderDetailMapper {

    @Select("select DISTINCT order_detail.id,good.name goodname,user.name username,order_detail.count from order_detail,good,user,orders where order_detail.good_id=good.id and order_detail.user_id=user.id and order_detail.order_id=#{id}")
    Page<OrderDetailEntity> getOrderDetailByOrderId(long id);

    @Delete("DELETE FROM order_detail WHERE id = #{id}")
    int deleteOrderDetailById(long id);

    @Delete("<script>DELETE FROM order_detail WHERE id in <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int batchDeleteOrderDetailById(int[] ids);

    @Insert("insert into order_detail(user_id, good_id, count, order_id, saler_id) values(#{user_id}, #{good_id}, #{count}, #{order_id}, #{saler_id})")
    int insertOrderDetail(OrderDetailEntity orderDetailEntity);

    @Select("select od.id,od.count,g.name goodname,s.name salername,u.name username from order_detail od,good g,user u,salers s where od.saler_id = #{id} and od.saler_id = s.id and od.good_id = g.id and od.user_id = u.id")
    Page<OrderDetailEntity> getOrderDetailBySalerId(long id);

    @Select("select od.id,od.count,g.name goodname,s.name salername,u.name username from order_detail od,good g,user u,salers s where od.good_id = #{id} and od.saler_id = s.id and od.good_id = g.id and od.user_id = u.id")
    Page<OrderDetailEntity> getOrderDetailByGoodId(long id);

    @Select("select od.id,od.count,g.name goodname,s.name salername,u.name username,od.price,c.item item from order_detail od,good g,user u,salers s,category c where od.user_id = #{id} and od.saler_id = s.id and od.good_id = g.id and od.user_id = u.id and od.category_id = c.id")
    Page<OrderDetailEntity> getOrderDetailByUserId(long id);

    @Select("select od.id,od.count,g.name goodname,s.name salername,u.name username,od.price,c.item item from order_detail od,good g,user u,salers s,category c where od.category_id = #{id} and od.saler_id = s.id and od.good_id = g.id and od.user_id = u.id and od.category_id = c.id")
    Page<OrderDetailEntity> getOrderDetailByCategoryId(long id);

}
