package az.orient.elibrarydemoboot.service;

import az.orient.elibrarydemoboot.dto.request.ReqCustomer;
import az.orient.elibrarydemoboot.dto.response.RespCustomer;
import az.orient.elibrarydemoboot.dto.response.Response;

import java.util.List;

public interface CustomerService {

    Response<List<RespCustomer>> getCustomerList();

    Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer);

    Response addCustomer(ReqCustomer reqCustomer);

    Response activateCustomer(String activationCode);
}
