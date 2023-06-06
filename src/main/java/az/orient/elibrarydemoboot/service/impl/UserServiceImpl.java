package az.orient.elibrarydemoboot.service.impl;

import az.orient.elibrarydemoboot.dto.request.ReqLogin;
import az.orient.elibrarydemoboot.dto.request.ReqToken;
import az.orient.elibrarydemoboot.dto.response.RespStatus;
import az.orient.elibrarydemoboot.dto.response.RespToken;
import az.orient.elibrarydemoboot.dto.response.RespUser;
import az.orient.elibrarydemoboot.dto.response.Response;
import az.orient.elibrarydemoboot.entity.User;
import az.orient.elibrarydemoboot.entity.UserToken;
import az.orient.elibrarydemoboot.enums.EnumAvailableStatus;
import az.orient.elibrarydemoboot.exception.ExceptionConstants;
import az.orient.elibrarydemoboot.exception.LibraryException;
import az.orient.elibrarydemoboot.repository.UserRepository;
import az.orient.elibrarydemoboot.repository.UserTokenRepository;
import az.orient.elibrarydemoboot.service.UserService;
import az.orient.elibrarydemoboot.util.Utility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserTokenRepository userTokenRepository;

    private final Utility utility;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Response<RespUser> login(ReqLogin reqLogin) {
        Response<RespUser> response = new Response<>();
        RespUser respUser = new RespUser();
        LOGGER.info("login request: " + reqLogin);
        try {
            String username = reqLogin.getUsername();
            String password = reqLogin.getPassword();
            if (username==null || password==null) {
                LOGGER.warn("login response: Invalid request data");
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            User user= userRepository.findUserByUsernameAndPasswordAndActive(username,password, EnumAvailableStatus.ACTIVE.value);
            if (user == null) {
                LOGGER.warn("login response: User not found");
                throw new LibraryException(ExceptionConstants.USER_NOT_FOUND, "User not found");
            }
            String token = UUID.randomUUID().toString();
            UserToken userToken = UserToken.builder()
                    .user(user)
                    .token(token)
                    .build();
            userTokenRepository.save(userToken);
            respUser.setUsername(username);
            respUser.setFullName(user.getFullName());
            respUser.setRespToken(new RespToken(user.getId(), token));
            response.setT(respUser);
            response.setStatus(RespStatus.getSuccessMessage());
            LOGGER.info("login success");
        } catch (LibraryException ex) {
            LOGGER.error("login error: " + ex);
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            LOGGER.error("login error: " + ex);
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response logout(ReqToken reqToken) {
        Response response = new Response<>();
        LOGGER.info("logout request: " + reqToken);
        try {
            UserToken userToken = utility.checkToken(reqToken);
            userToken.setActive(EnumAvailableStatus.DEACTIVE.value);
            userTokenRepository.save(userToken);
            response.setStatus(RespStatus.getSuccessMessage());
            LOGGER.info("logout success");
        } catch (LibraryException ex) {
            LOGGER.error("logout error: " + ex);
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            LOGGER.error("logout error: " + ex);
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }
}
