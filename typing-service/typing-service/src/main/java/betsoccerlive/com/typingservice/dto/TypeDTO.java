package betsoccerlive.com.typingservice.dto;

import betsoccerlive.com.typingservice.entity.MatchDetails;
import lombok.Data;

@Data
public class TypeDTO {

    private MatchDetails matchDetails;

    private Integer type;
    private Integer team_1_score;
    private Integer team_2_score;

}
