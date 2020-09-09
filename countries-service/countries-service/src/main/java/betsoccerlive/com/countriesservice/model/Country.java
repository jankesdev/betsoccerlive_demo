package betsoccerlive.com.countriesservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@Document(collection = "countries")
public class Country {

    @Id
    private String id;

    @NotNull
    private String country_name;

    @NotNull
    private String country_logo;

    private String api_1_id;

    private Boolean isTop;
    private Boolean isActive;
    private Date date;

}
