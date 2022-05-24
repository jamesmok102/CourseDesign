package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Record implements Serializable {

    private Long id;
    private String name;
    private String content;
    private Timestamp time;
    private String ip;

}
