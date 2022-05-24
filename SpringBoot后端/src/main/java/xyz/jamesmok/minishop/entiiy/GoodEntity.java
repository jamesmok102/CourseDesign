package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;
import java.sql.Blob;

@Data
public class GoodEntity implements Serializable {

    private long id;
    private String name;
    private long category_id;
    private String item;//category
    private String intro;
    private long browse;
    private Blob pic;
    private long saler_id;
    private String salername;
    private Double price;
    private long count;
    private long sell; //出售数

}
