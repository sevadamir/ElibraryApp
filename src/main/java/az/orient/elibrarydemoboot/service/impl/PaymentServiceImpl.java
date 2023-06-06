package az.orient.elibrarydemoboot.service.impl;

import az.orient.elibrarydemoboot.dto.request.ReqPayment;
import az.orient.elibrarydemoboot.dto.response.*;
import az.orient.elibrarydemoboot.entity.Book;
import az.orient.elibrarydemoboot.entity.Customer;
import az.orient.elibrarydemoboot.entity.Payment;
import az.orient.elibrarydemoboot.enums.EnumAvailableStatus;
import az.orient.elibrarydemoboot.exception.ExceptionConstants;
import az.orient.elibrarydemoboot.exception.LibraryException;
import az.orient.elibrarydemoboot.repository.BookRepository;
import az.orient.elibrarydemoboot.repository.PaymentRepository;
import az.orient.elibrarydemoboot.repository.CustomerRepository;
import az.orient.elibrarydemoboot.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final BookRepository bookRepository;

    private final CustomerRepository customerRepository;

    private final PaymentRepository paymentRepository;
    @Override
    public Response<List<RespPayment>> getPaymentByCustomerId(Long customerId) {
//        ReqBook reqBook = new ReqBook();
        Response<List<RespPayment>> response = new Response<>();
//        Long bookId = reqBook.getBookId();
        try {
            if (customerId == null ) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            //Book book = bookRepository.findBookByIdAndActive(bookId, EnumAvailableStatus.ACTIVE.value);
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new LibraryException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
//            if (book == null) {
//                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
//            }
            List<Payment> paymentList = paymentRepository.findAllByCustomerAndActive(customer, EnumAvailableStatus.ACTIVE.value);


            //List<User> userList= userRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (paymentList.isEmpty()) {
                throw new LibraryException(ExceptionConstants.PAYMENT_NOT_FOUND, "Payment not found");
            }
            List<RespPayment> respPaymentList = paymentList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respPaymentList);
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
    public Response addPayment(ReqPayment reqPayment) {
        Response response = new Response<>();
        try {
            Long customerId =  reqPayment.getCustomerId();
            Long bookId = reqPayment.getBookId();
            Integer amount = reqPayment.getAmount();
            if(customerId == null || bookId == null || amount == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Book book = bookRepository.findBookByIdAndActive(bookId, EnumAvailableStatus.ACTIVE.value);
            if (book == null) {
                throw new LibraryException(ExceptionConstants.BOOK_NOT_FOUND, "Book not found");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new LibraryException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            Payment payment = Payment.builder()
                    .customer(customer)
                    .book(book)
                    .amount(amount)
                    .build();
            paymentRepository.save(payment);
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

    private RespPayment mapping(Payment payment) {
//        RespBook respBook = RespBook.builder()
//                .name(payment.getBook().getName())
//                .author(payment.getBook().getAuthor())
//                .lang(payment.getBook().getLang())
//                .build();
        RespCustomer respCustomer = RespCustomer.builder()
                .name(payment.getCustomer().getName())
                .surname(payment.getCustomer().getSurname())
                .phone(payment.getCustomer().getPhone())
                .address(payment.getCustomer().getAddress())
                .dob(payment.getCustomer().getDob())
                .build();
        RespPayment respPayment = RespPayment.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .respCustomer(respCustomer)
//                .respBook(respBook)
                .build();
        return respPayment;
    }

}
