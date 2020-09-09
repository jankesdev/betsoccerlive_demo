package betsoccerlive.com.typingservice.service;

import betsoccerlive.com.typingservice.dto.TypeDTO;
import betsoccerlive.com.typingservice.exception.AlreadyExistsException;
import betsoccerlive.com.typingservice.exception.AlreadyUpdateException;
import betsoccerlive.com.typingservice.exception.BadRequestException;
import betsoccerlive.com.typingservice.exception.NotFoundException;
import betsoccerlive.com.typingservice.model.Type;
import betsoccerlive.com.typingservice.repository.TypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class TypingServiceImpl implements TypingService {

    TypeRepository typeRepository;

    public TypingServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type addType(TypeDTO typeDTO, String username) {

        if ((typeDTO.getType() == null && (typeDTO.getTeam_1_score() == null || typeDTO.getTeam_2_score() == null))) {
            throw new BadRequestException("No type parameter");
        }

        if (typeDTO.getMatchDetails() == null || typeDTO.getMatchDetails().getMatch_id() == null) {
            throw new BadRequestException("No match details parameter");
        }

        if (typeRepository.findTypeByUsernameAndMatchId(username, typeDTO.getMatchDetails().getMatch_id()).isPresent()) {
            throw new AlreadyExistsException("Type already exists in types for username: " + username);
        }

        Type saveTyped = Type.builder()
                .username(username)
                .matchDetails(typeDTO.getMatchDetails())
                .dateTyping(new Date())
                .dateCheck(null)
                .countUpdate(0)
                .score(null)
                .isCheck(false).build();

        if (typeDTO.getType() != null) {
            switch (typeDTO.getType()) {
                case 0:
                    saveTyped.setType(0);
                    break;
                case 1:
                    saveTyped.setType(1);
                    break;
                case 2:
                    saveTyped.setType(2);
                    break;
                default:
                    throw new BadRequestException("Bad format for parameter type");
            }
        } else if (typeDTO.getTeam_1_score() != null && typeDTO.getTeam_2_score() != null) {
            saveTyped.setTeam_1_score(typeDTO.getTeam_1_score());
            saveTyped.setTeam_2_score(typeDTO.getTeam_2_score());
        } else {
            throw new BadRequestException("No parameters");
        }

        return typeRepository.save(saveTyped);
    }

    @Override
    public Type updateType(String username, TypeDTO typeDTO) {
        if ((typeDTO.getType() == null && (typeDTO.getTeam_1_score() == null || typeDTO.getTeam_2_score() == null))) {
            throw new BadRequestException("No type parameter");
        }

        if (typeDTO.getMatchDetails() == null || typeDTO.getMatchDetails().getMatch_id() == null) {
            throw new BadRequestException("No match details parameter");
        }

        Optional<Type> optionalType = typeRepository.findTypeByUsernameAndMatchId(username, typeDTO.getMatchDetails().getMatch_id());

        if (!optionalType.isPresent()) {
            throw new NotFoundException("Type not found");
        }

        Type updateType = optionalType.get();

        if (updateType.getCountUpdate() >= 1) {
            throw new AlreadyUpdateException("Type already updated");
        }
        if (updateType.getIsCheck() == true) {
            throw new BadRequestException("Type already checked");
        }

        if (typeDTO.getType() != null) {
            switch (typeDTO.getType()) {
                case 0:
                    updateType.setType(0);
                    updateType.setTeam_1_score(null);
                    updateType.setTeam_2_score(null);
                    break;
                case 1:
                    updateType.setType(1);
                    updateType.setTeam_1_score(null);
                    updateType.setTeam_2_score(null);
                    break;
                case 2:
                    updateType.setType(2);
                    updateType.setTeam_1_score(null);
                    updateType.setTeam_2_score(null);
                    break;
                default:
                    throw new BadRequestException("Bad format for parameter type");
            }
        } else if (typeDTO.getTeam_1_score() != null && typeDTO.getTeam_2_score() != null) {
            updateType.setType(null);
            updateType.setTeam_1_score(typeDTO.getTeam_1_score());
            updateType.setTeam_2_score(typeDTO.getTeam_2_score());
        } else {
            throw new BadRequestException("No parameters");
        }

        updateType.setCountUpdate(updateType.getCountUpdate() + 1);
        return typeRepository.save(updateType);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Map<String, Boolean> deleteAll() {
        typeRepository.deleteAll();

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted all", Boolean.TRUE);

        return response;
    }
}
