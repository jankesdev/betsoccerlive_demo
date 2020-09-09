package betsoccerlive.com.friendsservice.api;

import betsoccerlive.com.friendsservice.model.Friends;
import betsoccerlive.com.friendsservice.service.FriendsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class FriendsController {

    private static String auth = "jankes";

    private FriendsServiceImpl friendsService;

    public FriendsController(FriendsServiceImpl friendsService) {
        this.friendsService = friendsService;
    }

    //todo auth admin
    @GetMapping("/admin/friends/all")
    public List<Friends> getAllFriends() {
        return friendsService.getAllFriends();
    }

    @GetMapping("/friends/{username}")
    public List<String> getFriends(@PathVariable(value = "username") String username) {
        return friendsService.getFriendsByUsername(username);
    }

    @PostMapping("/friends/{friend}/invite")
    @ResponseStatus(HttpStatus.CREATED)
    public Friends inviteFriend(@PathVariable(value = "friend") String friend) {
        String username = auth; //todo
        return friendsService.inviteFriend(username, friend);
    }

    @GetMapping("/friends/{friend}/acceptInvite")
    public Map<String, Boolean> acceptInviteFriend(@PathVariable(value = "friend") String friend) {
        String username = auth; //todo
        return friendsService.acceptInvite(username, friend);
    }

    @GetMapping("/friends/{friend}/removeInvite")
    public Map<String, Boolean> removeInviteFriend(@PathVariable(value = "friend") String friend) {
        String username = auth; //todo
        return friendsService.deleteInvite(username, friend);
    }

    @DeleteMapping("/friends/{friend}/delete")
    public Map<String, Boolean> deleteFriend(@PathVariable(value = "friend") String friend) {
        String username = auth; //todo
        return friendsService.deleteFriend(username, friend);
    }

    //todo auth admin
    @DeleteMapping("/admin/friends/all/delete")
    public Map<String, Boolean> deleteAllFriends() {
        return friendsService.deleteAll();
    }
}
