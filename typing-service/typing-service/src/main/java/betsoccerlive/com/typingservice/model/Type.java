package betsoccerlive.com.typingservice.model;

import betsoccerlive.com.typingservice.entity.MatchDetails;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@Document(collection = "types")
public class Type {

    @Id
    private String id;

    @NotNull
    private String username;

    private MatchDetails matchDetails;

    private Integer type;
    private Integer team_1_score;
    private Integer team_2_score;

    private Integer countUpdate;

    private Boolean isCheck;

    private Integer score;

    private Date dateCheck;
    private Date dateTyping;
}
