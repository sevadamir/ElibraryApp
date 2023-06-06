package az.orient.elibrarydemoboot.controller;

import az.orient.elibrarydemoboot.dto.request.ReqPayment;
import az.orient.elibrarydemoboot.dto.response.RespPayment;
import az.orient.elibrarydemoboot.dto.response.Response;
import az.orient.elibrarydemoboot.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @GetMapping("/GetPaymentListByUserId/{customerId}")
    public Response <List<RespPayment>> getPaymentListByCustomerId(@PathVariable Long customerId) {
        return paymentService.getPaymentByCustomerId(customerId);
    }

    @PostMapping("/AddPayment")
    public Response addPayment(@RequestBody ReqPayment reqPayment) {
        return paymentService.addPayment(reqPayment);
    }

}
