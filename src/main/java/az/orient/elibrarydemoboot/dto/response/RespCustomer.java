package az.orient.elibrarydemoboot.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespCustomer {

    private Long customerId;
    private String name;
    private String surname;
    private Date dob;
    private String address;
    private String phone;
    private String email;
    private String activationCode;

}
