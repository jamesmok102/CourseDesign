package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailEntity implements Serializable {

    private long id;
    private long user_id;
    private String username;
    private long good_id;
    private String goodname;
    private long count;
    private long order_id;
    private long saler_id;
    private String salername;
    private long price;
    private long category_id;
    private String item;

}
