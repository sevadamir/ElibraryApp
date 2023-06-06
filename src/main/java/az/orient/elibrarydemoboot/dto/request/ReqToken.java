package az.orient.elibrarydemoboot.dto.request;

import lombok.Data;

@Data
public class ReqToken {

    private Long userId;
    private String token;
}
