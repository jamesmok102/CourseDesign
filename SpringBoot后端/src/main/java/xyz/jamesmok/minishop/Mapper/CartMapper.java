package xyz.jamesmok.minishop.Mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.jamesmok.minishop.entiiy.CartEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {

    @Results({
            @Result(property = "good", column = "goodname"),
            @Result(property = "user", column = "username"),
    })
    @Select("select cart.id,good.name goodname,user.name username,cart.count,cart.good_id,cart.user_id from cart,good,user where cart.good_id=good.id and cart.user_id=user.id")
    Page<CartEntity> getCartAll();

    @Delete("DELETE FROM cart WHERE id = #{id}")
    int deleteCartById(long id);

    @Delete("<script>DELETE FROM cart WHERE id in <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int batchDeleteCartById(int[] ids);

    @Results({
            @Result(property = "good", column = "goodname"),
            @Result(property = "user", column = "username"),
    })
    @Select("select cart.id,good.name goodname,user.name username,cart.count,cart.good_id,cart.user_id,cart.selected from cart,good,user where cart.good_id=good.id and cart.user_id=user.id and user.id=#{id}")
    Page<CartEntity> getCartByUserId(long id);

    @Select("select cart.id,good.name goodname,user.name username,cart.count,cart.good_id,cart.user_id,cart.selected from cart,good,user where cart.good_id=good.id and cart.user_id=user.id and user.id=#{id}")
    List<CartEntity> getCartByUserIdNoPage(long id);

    @Select("select cart.id,good.name goodname,user.name username,cart.count from cart,good,user where cart.good_id=good.id and cart.user_id=user.id and user.id=#{user_id} and good_id=#{good_id}")
    CartEntity getCartByUserIdGoodId(long user_id, long good_id);

    @Insert("insert into cart(id, good_id, user_id, count, selected) values(#{id}, #{good_id}, #{user_id}, #{count}, #{selected})")
    int insertCart(CartEntity cart);

    @Update("update cart set count=#{count} where id=#{id}")
    int updateCart(CartEntity cart);

    @Select("select * from cart where id = #{id}")
    CartEntity getCartById(long id);

    @Update("<script>UPDATE cart SET selected=1 WHERE id in <foreach collection='list' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int change1SelectedById(List<Long> ids);

    @Update("<script>UPDATE cart SET selected=0 WHERE id in <foreach collection='list' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int change0SelectedById(List<Long> ids);

}
