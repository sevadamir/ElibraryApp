package az.orient.elibrarydemoboot.controller;

import az.orient.elibrarydemoboot.dto.request.ReqCustomer;
import az.orient.elibrarydemoboot.dto.response.RespCustomer;
import az.orient.elibrarydemoboot.dto.response.Response;
import az.orient.elibrarydemoboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/getCustomerList")
    public Response<List<RespCustomer>> getCustomerList() {
        return customerService.getCustomerList();
    }

    @PostMapping("/GetCustomerById")
    public Response<RespCustomer> getCustomerById(@RequestBody ReqCustomer reqCustomer) {
        return customerService.getCustomerById(reqCustomer);
    }

    @PostMapping("/addCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.addCustomer(reqCustomer);
    }

    @GetMapping("/activate/{activationCode}")
    public Response activate(@PathVariable String activationCode) {
        return customerService.activateCustomer(activationCode);
    }

}
