package az.orient.elibrarydemoboot.service;


import az.orient.elibrarydemoboot.dto.request.ReqPayment;
import az.orient.elibrarydemoboot.dto.response.RespPayment;
import az.orient.elibrarydemoboot.dto.response.Response;

import java.util.List;

public interface PaymentService {
    Response<List<RespPayment>> getPaymentByCustomerId(Long customerId);

    Response addPayment(ReqPayment reqPayment);
}
