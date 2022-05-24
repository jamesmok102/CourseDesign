package xyz.jamesmok.minishop.entiiy;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrowseRecordEntity implements Serializable {

    private Long id;
    private Long saler_id;
    private String salername;
    private Long user_id;
    private String username;
    private Long good_id;
    private String goodname;
    private Long browse;

}
