package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPortraitEntity implements Serializable {

    private String name;
    private String area;
    private Long power;
    private Long category_id;
    private String item;

}
