package az.orient.elibrarydemoboot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReqBook {

    private Long bookId;
    private String name;
    private String author;
    private String lang;
    private Integer year;
    private Integer amount;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
