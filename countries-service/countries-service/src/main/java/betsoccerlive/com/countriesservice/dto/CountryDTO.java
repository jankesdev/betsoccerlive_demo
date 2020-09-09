package betsoccerlive.com.countriesservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CountryDTO {

    private String country_id;
    private String country_name;
    private String country_logo;

    private String api_1_id;

    private Boolean isTop;
    private Boolean isActive;

}
