package betsoccerlive.com.friendsservice.service;

import betsoccerlive.com.friendsservice.model.Friends;

import java.util.List;
import java.util.Map;

public interface FriendsService {

    List<Friends> getAllFriends();
    List<String> getFriendsByUsername(String username);

    Friends inviteFriend(String username, String friend);

    Map<String, Boolean> acceptInvite(String username, String friend);
    Map<String, Boolean> deleteInvite(String username, String friend);
    Map<String, Boolean> deleteFriend(String username, String friend);
    Map<String, Boolean> deleteAll();
}
