package xyz.jamesmok.minishop.entiiy;


import lombok.Data;

import java.io.Serializable;
import java.sql.Blob;

@Data
public class UserEntity implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String area;
    private Blob avatar;
    private String phone;
    private long age;

}
