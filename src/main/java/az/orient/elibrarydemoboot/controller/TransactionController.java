package az.orient.elibrarydemoboot.controller;

import az.orient.elibrarydemoboot.dto.request.ReqTransaction;
import az.orient.elibrarydemoboot.dto.response.RespTransaction;
import az.orient.elibrarydemoboot.dto.response.Response;
import az.orient.elibrarydemoboot.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/GetTransactionList/{paymentId}")
    public Response<List<RespTransaction>> getTransactionList(@PathVariable Long paymentId) {

        return transactionService.getTransactionList(paymentId);
    }

    @PostMapping("/CreateOperation")
    public Response createOperation(@RequestBody ReqTransaction reqTransaction) {
        return transactionService.createOperation(reqTransaction);
    }

}
