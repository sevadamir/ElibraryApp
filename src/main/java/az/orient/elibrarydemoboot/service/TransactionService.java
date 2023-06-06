package az.orient.elibrarydemoboot.service;

import az.orient.elibrarydemoboot.dto.request.ReqTransaction;
import az.orient.elibrarydemoboot.dto.response.RespTransaction;
import az.orient.elibrarydemoboot.dto.response.Response;

import java.util.List;

public interface TransactionService {
    Response<List<RespTransaction>> getTransactionList(Long paymentId);

    Response createOperation(ReqTransaction reqTransaction);
}
