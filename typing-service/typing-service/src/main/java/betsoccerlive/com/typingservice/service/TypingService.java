package betsoccerlive.com.typingservice.service;

import betsoccerlive.com.typingservice.dto.TypeDTO;
import betsoccerlive.com.typingservice.model.Type;

import java.util.List;
import java.util.Map;

public interface TypingService {

    List<Type> getAllTypes();

    Type addType(TypeDTO typeDTO, String username);
    Type updateType(String username, TypeDTO typeDTO);

    Map<String, Boolean> deleteAll();

}
