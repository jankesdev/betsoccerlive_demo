package betsoccerlive.com.friendsservice.service;

import betsoccerlive.com.friendsservice.exception.AlreadyExistsException;
import betsoccerlive.com.friendsservice.exception.BadRequestException;
import betsoccerlive.com.friendsservice.exception.NotFoundException;
import betsoccerlive.com.friendsservice.model.Friends;
import betsoccerlive.com.friendsservice.repository.FriendsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class FriendsServiceImpl implements FriendsService {

    private FriendsRepository friendsRepository;

    public FriendsServiceImpl(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    @Override
    public List<String> getFriendsByUsername(String username) {
        Set<Friends> friends = friendsRepository.findFriendsByUsername(username);
        Set<String> friendsResponse = new HashSet<>();
        for (Friends fr : friends) {
            if (fr.getUser_1_id().equals(username)) {
                friendsResponse.add(fr.getUser_2_id());
            } else {
                friendsResponse.add(fr.getUser_1_id());
            }
        }
        List<String> response = new ArrayList<>();
        response.addAll(friendsResponse);

        Collections.sort(response);

        return response;
    }

    @Override
    public Map<String, Boolean> deleteFriend(String username, String friend) {
        username = username.toLowerCase();
        friend = friend.toLowerCase();

        if (username.equals(friend)) {
            throw new BadRequestException("Bad request");
        }

        Optional<Friends> friends = friendsRepository.findFriendsByUsernameAndFriend(username, friend);

        if (friends.isPresent()) {
            friendsRepository.delete(friends.get());
        } else {
            throw new NotFoundException("Friend not found with that username: " + friend);
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @Override
    public Friends inviteFriend(String username, String friend) {
        username = username.toLowerCase();
        friend = friend.toLowerCase();

        if (username.equals(friend)) {
            throw new BadRequestException("Bad request");
        }

        Optional<Friends> friends = friendsRepository.findFriendsByUsernameAndFriend(username, friend);

        if (friends.isPresent()) {
            throw new AlreadyExistsException("The invitation has already been used");
        }

        Friends saveFriends = Friends.builder()
                .user_1_id(username)
                .user_2_id(friend)
                .date(new Date())
                .isActive(false)
                .build();

        return friendsRepository.save(saveFriends);
    }

    @Override
    public Map<String, Boolean> acceptInvite(String username, String friend) {
        username = username.toLowerCase();
        friend = friend.toLowerCase();

        if (username.equals(friend)) {
            throw new BadRequestException("Bad request");
        }

        Optional<Friends> friends = friendsRepository.findFriendsByUsernameAndFriend(username.toLowerCase(), friend.toLowerCase());
        String key = "";

        if (!friends.isPresent()) {
            throw new NotFoundException("Friend not found with that username: " + friend);
        }

        if (friends.get().isActive()) {
            throw new AlreadyExistsException("The invitation has already been accepted");
        }

        friends.get().setActive(true);
        friends.get().setDate(new Date());

        friendsRepository.save(friends.get());

        Map<String, Boolean> response = new HashMap<>();
        response.put("accepted", Boolean.TRUE);

        return response;
    }

    @Override
    public Map<String, Boolean> deleteInvite(String username, String friend) {
        if (username.equals(friend)) {
            throw new BadRequestException("Bad request");
        }

        Optional<Friends> optionalFriends = friendsRepository.findFriendsByUsernameAndFriend(username, friend);

        if (!optionalFriends.isPresent()) {
            throw new NotFoundException("Not found invite friends from username " + username + " and " + friend);
        }

        if (optionalFriends.get().isActive()) {
            throw new BadRequestException("Friends already activated");
        }

        friendsRepository.delete(optionalFriends.get());

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted all", Boolean.TRUE);

        return response;
    }

    @Override
    public List<Friends> getAllFriends() {
        return friendsRepository.findAll();
    }

    @Override
    public Map<String, Boolean> deleteAll() {
        friendsRepository.deleteAll();

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted all", Boolean.TRUE);

        return response;
    }

}