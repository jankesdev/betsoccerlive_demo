package betsoccerlive.com.countriesservice.service;

import betsoccerlive.com.countriesservice.dto.CountryDTO;
import betsoccerlive.com.countriesservice.exception.AlreadyExistsException;
import betsoccerlive.com.countriesservice.exception.BadRequestException;
import betsoccerlive.com.countriesservice.exception.NotFoundException;
import betsoccerlive.com.countriesservice.model.Country;
import betsoccerlive.com.countriesservice.repository.CountriesRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountriesServiceImpl implements CountriesService {

    CountriesRepository countriesRepository;

    public CountriesServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public Country getCountryByName(String country_name) {
        Optional<Country> optionalCountry = countriesRepository.findCountryByCountryName(country_name);
        if (!optionalCountry.isPresent()) {
            throw new NotFoundException("Country not found by country_name: " + country_name);
        }
        return optionalCountry.get();
    }

    @Override
    public Country getCountryById(String id) {
        Optional<Country> optionalCountry = countriesRepository.findById(id);
        if (!optionalCountry.isPresent()) {
            throw new NotFoundException("Country not found by id: " + id);
        }
        return optionalCountry.get();
    }

    @Override
    public List<Country> getAllCountries() {
        List<Country> countries = countriesRepository.findAll();

        Comparator<Country> compareByName = (Country c1, Country c2) ->
                c1.getCountry_name().compareTo(c2.getCountry_name());

        Collections.sort(countries, compareByName);

        return countries;
    }

    @Override
    public List<Country> getAllCountriesActive() {
        List<Country> countries = countriesRepository.findAllByActive();

        Comparator<Country> compareByName = (Country c1, Country c2) ->
                c1.getCountry_name().compareTo(c2.getCountry_name());

        Collections.sort(countries, compareByName);

        return countries;
    }

    @Override
    public List<Country> getTopCountries() {
        List<Country> topCountries = countriesRepository.findTopCountries();

        Comparator<Country> compareByName = (Country c1, Country c2) ->
                c1.getCountry_name().compareTo(c2.getCountry_name());

        Collections.sort(topCountries, compareByName);

        return topCountries;
    }

    @Override
    public Boolean activeCountryByName(String country_name) {
        Optional<Country> optionalCountry = countriesRepository.findCountryByCountryName(country_name);
        boolean isActive;

        if (!optionalCountry.isPresent()) {
            throw new NotFoundException("Not found country");
        }

        Country saveCountry = optionalCountry.get();

        if (saveCountry.getIsActive()) {
            isActive = false;
        } else {
            isActive = true;
        }

        saveCountry.setIsActive(isActive);
        countriesRepository.save(saveCountry);

        return isActive;
    }

    @Override
    public Boolean topCountryByName(String country_name) {
        Optional<Country> optionalCountry = countriesRepository.findCountryByCountryName(country_name);
        boolean isTop;

        if (!optionalCountry.isPresent()) {
            throw new NotFoundException("Not found country");
        }

        Country saveCountry = optionalCountry.get();

        if (saveCountry.getIsTop()) {
            isTop = false;
        } else {
            isTop = true;
        }

        saveCountry.setIsTop(isTop);
        countriesRepository.save(saveCountry);

        return isTop;
    }

    @Override
    public Country addCountry(CountryDTO countryDTO) {
        if (countryDTO.getCountry_id() == null || countryDTO.getCountry_name() == null || countryDTO.getCountry_logo() == null) {
            throw new BadRequestException("No parameters");
        }

        if (countriesRepository.existsById(countryDTO.getCountry_id())) {
            throw new AlreadyExistsException("Already country id exists");
        }

        Optional<Country> optionalCountry = countriesRepository.findCountryByCountryName(countryDTO.getCountry_name());

        if (optionalCountry.isPresent()) {
            throw new AlreadyExistsException("Already country exists");
        }

        Country saveCountry = Country.builder()
                .id(countryDTO.getCountry_id())
                .country_name(countryDTO.getCountry_name())
                .country_logo(countryDTO.getCountry_logo())
                .api_1_id(countryDTO.getApi_1_id())
                .date(new Date())
                .isTop(false)
                .isActive(true)
                .build();

        if (saveCountry.getApi_1_id() == null) {
            saveCountry.setApi_1_id(saveCountry.getId());
        }

        if (countryDTO.getIsActive() != null) {
            saveCountry.setIsActive(countryDTO.getIsActive());
        }

        if (countryDTO.getIsTop() != null) {
            saveCountry.setIsTop(countryDTO.getIsTop());
        }

        return countriesRepository.save(saveCountry);
    }

    @Override
    public List<Country> addListCountry(List<CountryDTO> countryDTOList) {
        List<Country> countryList = new ArrayList<>();
        for (CountryDTO countryDTO : countryDTOList) {
            countryList.add(addCountry(countryDTO));
        }
        return countryList;
    }

    @Override
    public Country updateCountryByName(CountryDTO countryDTO, String country_name) {
        Optional<Country> optionalCountry = countriesRepository.findCountryByCountryName(country_name);

        if (!optionalCountry.isPresent()) {
            throw new NotFoundException("Not found country");
        }

        if (countryDTO.getCountry_name() == null || countryDTO.getCountry_logo() == null || country_name == null) {
            throw new BadRequestException("No parameters");
        }

        if (!country_name.equals(countryDTO.getCountry_name()) && countriesRepository.findCountryByCountryName(countryDTO.getCountry_name()).isPresent()) {
            throw new AlreadyExistsException("Country name is already exists");
        }

        Country saveCountry = optionalCountry.get();

        saveCountry.setCountry_name(countryDTO.getCountry_name());
        saveCountry.setCountry_logo(countryDTO.getCountry_logo());
        saveCountry.setApi_1_id(countryDTO.getApi_1_id());

        if (countryDTO.getIsActive() != null) {
            saveCountry.setIsActive(countryDTO.getIsActive());
        }

        if (countryDTO.getIsTop() != null) {
            saveCountry.setIsTop(countryDTO.getIsTop());
        }

        return countriesRepository.save(saveCountry);
    }

    @Override
    public Country updateCountryById(CountryDTO countryDTO, String id) {
        Optional<Country> optionalCountry = countriesRepository.findById(id);

        if (!optionalCountry.isPresent()) {
            throw new NotFoundException("Not found country");
        }

        if (countryDTO.getCountry_name() == null || countryDTO.getCountry_logo() == null || id == null) {
            throw new BadRequestException("No parameters");
        }

        if (!optionalCountry.get().getCountry_name().equals(countryDTO.getCountry_name()) && countriesRepository.findCountryByCountryName(countryDTO.getCountry_name()).isPresent()) {
            throw new AlreadyExistsException("Country name is already exists");
        }

        Country saveCountry = optionalCountry.get();

        saveCountry.setCountry_name(countryDTO.getCountry_name());
        saveCountry.setCountry_logo(countryDTO.getCountry_logo());
        saveCountry.setApi_1_id(countryDTO.getApi_1_id());

        if (countryDTO.getIsActive() != null) {
            saveCountry.setIsActive(countryDTO.getIsActive());
        }

        if (countryDTO.getIsTop() != null) {
            saveCountry.setIsTop(countryDTO.getIsTop());
        }

        return countriesRepository.save(saveCountry);
    }

    @Override
    public Map<String, Boolean> deleteAll() {
        countriesRepository.deleteAll();

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted all", Boolean.TRUE);

        return response;
    }

    @Override
    public Map<String, Boolean> deleteCountryByName(String country_name) {
        Optional<Country> country = countriesRepository.findCountryByCountryName(country_name);

        if (country.isPresent()) {
            countriesRepository.delete(country.get());
        } else {
            throw new NotFoundException("Country not found with that country_name: " + country_name);
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @Override
    public Map<String, Boolean> deleteCountryById(String id) {
        Optional<Country> country = countriesRepository.findById(id);

        if (country.isPresent()) {
            countriesRepository.delete(country.get());
        } else {
            throw new NotFoundException("Country not found with that country id: " + id);
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}