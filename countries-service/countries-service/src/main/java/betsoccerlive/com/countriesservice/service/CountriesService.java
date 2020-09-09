package betsoccerlive.com.countriesservice.service;

import betsoccerlive.com.countriesservice.dto.CountryDTO;
import betsoccerlive.com.countriesservice.model.Country;

import java.util.List;
import java.util.Map;

public interface CountriesService {

    Country getCountryById(String id);
    Country getCountryByName(String country_name);

    List<Country> getAllCountries();
    List<Country> getAllCountriesActive();
    List<Country> getTopCountries();

    Boolean activeCountryByName(String country_name);
    Boolean topCountryByName(String country_name);

    Country addCountry(CountryDTO countryDTO);
    List<Country> addListCountry(List<CountryDTO> countryDTOList);

    Country updateCountryById(CountryDTO countryDTO, String id);
    Country updateCountryByName(CountryDTO countryDTO, String country_name);

    Map<String, Boolean> deleteCountryById(String id);
    Map<String, Boolean> deleteCountryByName(String country_name);

    Map<String, Boolean> deleteAll();


}
