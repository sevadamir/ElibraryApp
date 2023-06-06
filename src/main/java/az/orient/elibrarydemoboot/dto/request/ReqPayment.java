package az.orient.elibrarydemoboot.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqPayment {

    private Long id;
    private Long customerId;
    private Long bookId;
    private Integer amount;

}
