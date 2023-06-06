package az.orient.elibrarydemoboot.dto.request;

import az.orient.elibrarydemoboot.entity.Payment;
import lombok.Data;

@Data
public class ReqTransaction {

    private Long paymentId;
    private Integer amount;

}
