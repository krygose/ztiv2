package com.example.zti.service.user;

import com.example.zti.common.ShortUuidGenerator;
import com.example.zti.common.problem.DuplicateKeyErrorProblem;
import com.example.zti.service.user.model.NewUserDto;
import com.example.zti.service.user.model.UserDto;
import com.example.zti.service.user.problem.EmailAddressNotFoundProblem;
import com.example.zti.service.user.problem.InvalidEmailAddressProblem;
import com.example.zti.service.user.sql.UserSqlService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserSqlService userSqlService;

    public UserService(UserSqlService userSqlService) {
        this.userSqlService = userSqlService;
    }

    public NewUserDto registerUser(NewUserDto userDto) {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (userDto.email() != null) {
            if (!pattern.matcher(userDto.email().toLowerCase()).matches()) throw new InvalidEmailAddressProblem();
        }

        Integer count = userSqlService.getCountByEmail(userDto.email().toLowerCase());
        if (count > 0) throw new DuplicateKeyErrorProblem();

        String id = String.format("%s.%s.%s", userDto.firstName(), ShortUuidGenerator.generateRandomString(4), "id");

        userSqlService.createUser(
                id,
                userDto.firstName(),
                userDto.lastName(),
                userDto.email(),
                userDto.password(),
                userDto.role() != null ? userDto.role() : "user");

        return userDto;
    }

    public UserDto getUserByEmail(Principal principal) {
        try {
            return userSqlService.getUserByEmail(principal.getName());
        } catch (NullPointerException e) {
            throw new EmailAddressNotFoundProblem();
        }
    }
}
