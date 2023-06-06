package az.orient.elibrarydemoboot.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespPayment {

    private Long id;
    private Integer amount;
    private RespCustomer respCustomer;
//    private RespBook respBook;

}
