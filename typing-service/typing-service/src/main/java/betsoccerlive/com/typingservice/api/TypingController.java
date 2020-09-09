package betsoccerlive.com.typingservice.api;

import betsoccerlive.com.typingservice.dto.TypeDTO;
import betsoccerlive.com.typingservice.exception.BadRequestException;
import betsoccerlive.com.typingservice.model.Type;
import betsoccerlive.com.typingservice.service.TypingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class TypingController {

    private static String auth = "jankes";

    TypingServiceImpl typingService;

    public TypingController(TypingServiceImpl typingService) {
        this.typingService = typingService;
    }

    //todo auth admin
    @GetMapping("/admin/types/all")
    public List<Type> getAllTypes() {
        return typingService.getAllTypes();
    }

    //todo auth admin
    @DeleteMapping("/admin/types/all/delete")
    public Map<String, Boolean> deleteAllTypes() {
        return typingService.deleteAll();
    }

    @PostMapping("/typing")
    @ResponseStatus(HttpStatus.CREATED)
    public Type addType(@RequestBody TypeDTO typeDTO) {
        String username = auth; //todo
        return typingService.addType(typeDTO, username);
    }

    @PutMapping("/typing")
    public Type updateType(@RequestBody TypeDTO typeDTO) {
        String username = auth; //todo
        return typingService.updateType(username, typeDTO);
    }

}
