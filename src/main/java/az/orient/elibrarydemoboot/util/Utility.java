package az.orient.elibrarydemoboot.util;

import az.orient.elibrarydemoboot.dto.request.ReqToken;
import az.orient.elibrarydemoboot.entity.User;
import az.orient.elibrarydemoboot.entity.UserToken;
import az.orient.elibrarydemoboot.enums.EnumAvailableStatus;
import az.orient.elibrarydemoboot.exception.ExceptionConstants;
import az.orient.elibrarydemoboot.exception.LibraryException;
import az.orient.elibrarydemoboot.repository.UserRepository;
import az.orient.elibrarydemoboot.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {

    private final UserRepository userRepository;

    private final UserTokenRepository userTokenRepository;

    public UserToken checkToken(ReqToken reqToken) {
        Long userId = reqToken.getUserId();
        String token = reqToken.getToken();
        if (userId == null || token == null) {
            throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Imvalid request data");
        }
        User user = userRepository.findUserByIdAndActive(userId, EnumAvailableStatus.ACTIVE.value);
        if (user == null) {
            throw new LibraryException(ExceptionConstants.USER_NOT_FOUND, "User not found");
        }
        UserToken userToken = userTokenRepository.findUserTokenByUserAndTokenAndActive(user, token, EnumAvailableStatus.ACTIVE.value);
        if (userToken == null) {
            throw new LibraryException(ExceptionConstants.INVALID_TOKEN, "Invalid token");
        }
        return userToken;
    }

}
