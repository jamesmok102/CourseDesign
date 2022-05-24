package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class OrderEntity implements Serializable {

    private long id;
    private long user_id;
    private String username;
    private Timestamp time;
    private String address;

}
