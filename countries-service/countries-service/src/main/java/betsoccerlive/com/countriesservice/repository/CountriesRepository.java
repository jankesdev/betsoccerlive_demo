package betsoccerlive.com.countriesservice.repository;

import betsoccerlive.com.countriesservice.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountriesRepository extends MongoRepository<Country, String> {

    @Query("{country_name : ?0}")
    Optional<Country> findCountryByCountryName(String country_name);

    @Query("{isActive : true}")
    List<Country> findAllByActive();

    @Query("{isTop : true}")
    List<Country> findTopCountries();

}
