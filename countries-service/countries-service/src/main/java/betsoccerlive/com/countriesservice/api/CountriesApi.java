package betsoccerlive.com.countriesservice.api;

import betsoccerlive.com.countriesservice.dto.CountryDTO;
import betsoccerlive.com.countriesservice.model.Country;
import betsoccerlive.com.countriesservice.service.CountriesServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class CountriesApi {

    private static String auth = "admin";

    CountriesServiceImpl countriesService;

    public CountriesApi(CountriesServiceImpl countriesService) {
        this.countriesService = countriesService;
    }

    //todo auth admin
    @GetMapping("/countries/all")
    public List<Country> getAllCountriesActive() {
        return countriesService.getAllCountriesActive();
    }

    //todo auth admin
    @GetMapping("/admin/countries/all")
    public List<Country> getAllCountries() {
        return countriesService.getAllCountries();
    }

    @GetMapping("/countries/top")
    public List<Country> getTopCountries() {
        return countriesService.getTopCountries();
    }

    //todo auth admin
    @GetMapping("/admin/countries/id/{country_id}")
    public Country getCountryById(@PathVariable(value = "country_id") String country_id) {
        return countriesService.getCountryById(country_id);
    }

    //todo auth admin
    @GetMapping("/admin/countries/{country_name}")
    public Country getCountryByName(@PathVariable(value = "country_name") String country_name) {
        return countriesService.getCountryByName(country_name);
    }

    //todo auth admin
    @GetMapping("/admin/countries/{country_name}/active")
    public Boolean activeCountryByName(@PathVariable(value = "country_name") String country_name) {
        return countriesService.activeCountryByName(country_name);
    }

    //todo auth admin
    @GetMapping("/admin/countries/{country_name}/top")
    public Boolean topCountryByName(@PathVariable(value = "country_name") String country_name) {
        return countriesService.topCountryByName(country_name);
    }

    //todo auth admin
    @PostMapping("/admin/countries/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Country addCountry(@RequestBody CountryDTO countryDTO) {
        String username = auth; //todo
        return countriesService.addCountry(countryDTO);
    }

    //todo auth admin
    @PostMapping("/admin/countries/addList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Country> addListCountry(@RequestBody List<CountryDTO> countryDTOList) {
        String username = auth; //todo
        return countriesService.addListCountry(countryDTOList);
    }

    //todo auth admin
    @PutMapping("/admin/countries/{country_name}/update")
    public Country updateCountry(@RequestBody CountryDTO countryDTO, @PathVariable(value = "country_name") String country_name) {
        String username = auth; //todo
        return countriesService.updateCountryByName(countryDTO, country_name);
    }

    //todo auth admin
    @PutMapping("/admin/countries/id/{country_id}/update")
    public Country updateCountryById(@RequestBody CountryDTO countryDTO, @PathVariable(value = "country_id") String country_id) {
        String username = auth; //todo
        return countriesService.updateCountryById(countryDTO, country_id);
    }

    //todo auth admin
    @DeleteMapping("/admin/countries/all/delete")
    public Map<String, Boolean> deleteAllCountries() {
        return countriesService.deleteAll();
    }

    //todo auth admin
    @DeleteMapping("/admin/countries/{country_name}/delete")
    public Map<String, Boolean> deleteCountryByName(@PathVariable(value = "country_name") String country_name) {
        String username = auth; //todo
        return countriesService.deleteCountryByName(country_name);
    }

    //todo auth admin
    @DeleteMapping("/admin/countries/id/{country_id}/delete")
    public Map<String, Boolean> deleteCountryById(@PathVariable(value = "country_id") String country_id) {
        String username = auth; //todo
        return countriesService.deleteCountryById(country_id);
    }

}