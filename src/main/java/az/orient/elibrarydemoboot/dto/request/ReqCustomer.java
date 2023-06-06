package az.orient.elibrarydemoboot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ReqCustomer {

    private Long customerId;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private Date dob;
    private String email;
    private String activationCode;
    @JsonProperty(value = "token")
    private ReqToken reqToken;
}
