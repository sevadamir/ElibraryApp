package az.orient.elibrarydemoboot.service.impl;

import az.orient.elibrarydemoboot.dto.request.ReqCustomer;
import az.orient.elibrarydemoboot.dto.request.ReqToken;
import az.orient.elibrarydemoboot.dto.response.RespStatus;
import az.orient.elibrarydemoboot.dto.response.RespCustomer;
import az.orient.elibrarydemoboot.dto.response.Response;
import az.orient.elibrarydemoboot.entity.Customer;
import az.orient.elibrarydemoboot.enums.EnumAvailableStatus;
import az.orient.elibrarydemoboot.exception.ExceptionConstants;
import az.orient.elibrarydemoboot.exception.LibraryException;
import az.orient.elibrarydemoboot.repository.CustomerRepository;
import az.orient.elibrarydemoboot.service.CustomerService;
import az.orient.elibrarydemoboot.util.Utility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final Utility utility;

    private final MailSender mailSender;

    private final EmailSender emailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();
        LOGGER.info("getCustomerList request: " + response);
        try {
            List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (customerList.isEmpty()) {
                LOGGER.warn("getCustomerList response: Customer not found");
                throw new LibraryException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            List<RespCustomer> respCustomerList = customerList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respCustomerList);
            response.setStatus(RespStatus.getSuccessMessage());
            LOGGER.info("getCustomerList response: success" + respCustomerList);
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
    public Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer) {
        Response<RespCustomer> response = new Response<>();
        try {
            Long customerId = reqCustomer.getCustomerId();
            utility.checkToken(reqCustomer.getReqToken());
            if (customerId == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new LibraryException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            RespCustomer respCustomer = mapping(customer);
            response.setT(respCustomer);
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
    public Response addCustomer(ReqCustomer reqCustomer) {
        Customer customer = new Customer();
        Response response =new Response<>();
        try {
            ReqToken reqToken = reqCustomer.getReqToken();
            utility.checkToken(reqToken);
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            String email = reqCustomer.getEmail();
            if (name == null || surname == null || email == null) {
                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customerFromDb = customerRepository.findCustomerByEmailAndActive(email, EnumAvailableStatus.ACTIVE.value);
            if (customerFromDb != null) {
                throw new LibraryException(ExceptionConstants.CUSTOMER_NOT_ADDED, "Customer not added");
            }
            customer = Customer.builder().
                    name(name).
                    surname(surname).
                    dob(reqCustomer.getDob()).
                    phone(reqCustomer.getPhone()).
                    address(reqCustomer.getAddress()).
                    email(email).
                    activationCode(UUID.randomUUID().toString()).
                    activationStatus(EnumAvailableStatus.ACTIVE.value).
                    build();
            customerRepository.save(customer);
//            customer.setActivationCode(UUID.randomUUID().toString());
//            String message = String.format(
//                    "Hello, %s! \n" + "Welcome to E-library. Please, visit next link: http://localhost:8082/activate/%s",
//                    customer.getName(),
//                    customer.getActivationCode()
//            );
//            mailSender.sendMail(customer.getEmail(), "Activation code", message);
            emailSender.sendEmail(customer.getEmail(),customer.getActivationCode());
//            if (!StringUtils.isEmpty(customer.getEmail())) {
//                String messag = String.format(
//                        "Hello, %s! \n" + "Welcome to E-library. Please, visit next link: http://localhost:8082/activate/%s",
//                        customer.getName(),
//                        customer.getActivationCode()
//                );
        //}
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
    public Response activateCustomer(String activationCode) {
        Response response = new Response<>();
        Customer customer = customerRepository.findByActivationCodeAndActive(activationCode, EnumAvailableStatus.ACTIVE.value);
        if (customer == null) {
            throw new LibraryException(ExceptionConstants.INVALID_ACTIVATION_CODE, "Invalid activation code");
        }
        customer.setActivationStatus(EnumAvailableStatus.INPROGRESS.value);
        customerRepository.save(customer);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    private RespCustomer mapping (Customer customer) {
        RespCustomer respCustomer = RespCustomer.builder()
                .customerId(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .dob(customer.getDob())
                .build();
        return respCustomer;
    }

}
