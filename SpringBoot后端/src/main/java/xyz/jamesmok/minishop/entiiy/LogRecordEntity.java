package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class LogRecordEntity implements Serializable {

    private Long id;
    private String state;
    private String role;
    private Timestamp time;
    private String ip;
    private String name;


}
