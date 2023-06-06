package az.orient.elibrarydemoboot.service;

import az.orient.elibrarydemoboot.dto.request.ReqLogin;
import az.orient.elibrarydemoboot.dto.request.ReqToken;
import az.orient.elibrarydemoboot.dto.response.RespUser;
import az.orient.elibrarydemoboot.dto.response.Response;

public interface UserService {
    Response<RespUser> login(ReqLogin reqLogin);

    Response logout(ReqToken reqToken);
}
