package betsoccerlive.com.typingservice.repository;

import betsoccerlive.com.typingservice.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends MongoRepository<Type, String> {

    @Query("{$and: [{username : ?0},{'matchDetails.match_id' : ?1}] }")
    Optional<Type> findTypeByUsernameAndMatchId(String username, String match_id);

}
