package betsoccerlive.com.typingservice.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MatchDetails {

    private String match_id;

    private Date match_date;

    private String team_1_id;
    private String team_2_id;

    private String team_1_name;
    private String team_2_name;

    private String team_1_logo;
    private String team_2_logo;

    private String country_id;
    private String league_id;

    private String team_1_result;
    private String team_2_result;

}
