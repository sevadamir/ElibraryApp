package az.orient.elibrarydemoboot.service.impl;

import az.orient.elibrarydemoboot.dto.request.ReqTransaction;
import az.orient.elibrarydemoboot.dto.response.*;
import az.orient.elibrarydemoboot.entity.Payment;
import az.orient.elibrarydemoboot.entity.Transaction;
import az.orient.elibrarydemoboot.enums.EnumAvailableStatus;
import az.orient.elibrarydemoboot.exception.ExceptionConstants;
import az.orient.elibrarydemoboot.exception.LibraryException;
import az.orient.elibrarydemoboot.repository.PaymentRepository;
import az.orient.elibrarydemoboot.repository.TransactionRepository;
import az.orient.elibrarydemoboot.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Response<List<RespTransaction>> getTransactionList(Long paymentId) {
        Response<List<RespTransaction>> response = new Response<>();
        try {
            if (paymentId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Payment payment = paymentRepository.findPaymentByIdAndActive(paymentId, EnumAvailableStatus.ACTIVE.value);
            if (payment == null) {
                throw new LibraryException(ExceptionConstants.PAYMENT_NOT_FOUND, "Payment not found");
            }
            List <Transaction> transactionList = transactionRepository.findAllByPaymentAndActive(payment, EnumAvailableStatus.ACTIVE.value);
            List<RespTransaction> respTransactionList = transactionList.stream().map(this::mapping).collect(Collectors.toList());
            if (respTransactionList.isEmpty()) {
                throw new LibraryException(ExceptionConstants.TRANSACTION_NOT_FOUND, "Transaction not found");
            }
            response.setT(respTransactionList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response createOperation(ReqTransaction reqTransaction) {
        Response response = new Response<>();
        try {
            Integer amount = reqTransaction.getAmount();
            Long paymentId = reqTransaction.getPaymentId();
            if (amount == null || paymentId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            if (amount <= 0) {
                throw new LibraryException(ExceptionConstants.INVALID_AMOUNT, "Invalid amount");
            }
            Payment payment = paymentRepository.findPaymentByIdAndActive(paymentId, EnumAvailableStatus.ACTIVE.value);
            if (payment == null) {
                throw new LibraryException(ExceptionConstants.PAYMENT_NOT_FOUND, "Payment not found");
            }
            Transaction transaction = new Transaction();
            transaction.builder()
                    .payment(payment)
                    .amount(amount)
                    .build();
            transactionRepository.save(transaction);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (LibraryException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
            ex.printStackTrace();
        }
        return response;
    }

    private RespTransaction mapping( Transaction transaction) {
        RespCustomer respCustomer = RespCustomer.builder()
                .customerId(transaction.getPayment().getCustomer().getId())
                .name(transaction.getPayment().getCustomer().getName())
                .surname(transaction.getPayment().getCustomer().getSurname())
                .address(transaction.getPayment().getCustomer().getAddress())
                .dob(transaction.getPayment().getCustomer().getDob())
                .phone(transaction.getPayment().getCustomer().getPhone())
                .build();
        RespPayment respPayment = RespPayment.builder()
                .amount(transaction.getPayment().getAmount())
                .respCustomer(respCustomer)
                .build();
        RespTransaction respTransaction = RespTransaction.builder()
                .amount(transaction.getAmount())
                .payment(respPayment)
                .build();
        return respTransaction;
    }

}
