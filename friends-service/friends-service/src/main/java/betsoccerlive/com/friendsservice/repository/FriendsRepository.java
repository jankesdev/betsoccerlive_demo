package betsoccerlive.com.friendsservice.repository;

import betsoccerlive.com.friendsservice.model.Friends;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FriendsRepository extends MongoRepository<Friends, String> {

    @Query("{$and: [ {$or:[{user_1_id : ?0},{user_2_id : ?0}]} , {isActive: {$eq: true}} ] }")
    Set<Friends> findFriendsByUsername(String username);
    @Query("{$or: [ {$and:[{user_1_id : ?0},{user_2_id : ?1}]} , {$and:[{user_1_id : ?1},{user_2_id : ?0}]} ] }")
    Optional<Friends> findFriendsByUsernameAndFriend(String username, String friend);

}