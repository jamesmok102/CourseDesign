package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartEntity implements Serializable {

    private Long id;
    private Long good_id;
    private String good;
    private Long user_id;
    private String user;
    private Long count;
    private Long selected;

}
