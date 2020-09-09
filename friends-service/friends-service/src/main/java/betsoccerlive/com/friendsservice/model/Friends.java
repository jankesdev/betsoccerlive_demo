package betsoccerlive.com.friendsservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@Document(collection = "friends")
public class Friends {

    @Id
    private String id;

    @NotNull
    private String user_1_id;

    @NotNull
    private String user_2_id;

    private Date date;
    private boolean isActive;

}
