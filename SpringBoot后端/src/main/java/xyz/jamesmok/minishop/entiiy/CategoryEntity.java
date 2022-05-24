package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryEntity implements Serializable {

    private long id;
    private String item;
    private long count;

}
