//package az.orient.elibrarydemoboot.controller;
//
//import az.orient.elibrarydemoboot.dto.request.ReqCustomer;
//import az.orient.elibrarydemoboot.dto.response.Response;
//import az.orient.elibrarydemoboot.entity.Customer;
//import az.orient.elibrarydemoboot.service.CustomerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Map;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/reg")
//public class RegistrationController {
//    private final CustomerService customerService;
//    private final ReqCustomer reqCustomer;
//
//    @GetMapping("/registration")
//    public String registration() {
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addCustomer(Customer customer, Map<String, Object> model) {
//        if (customerService.addCustomer(reqCustomer) == null) {
//            model.put("message", "Customer exists!");
//            return "registration";
//        }
//
//        return "redirect:/login";
//    }
//
//    @GetMapping("/activate/{activationCode}")
//    public Response activate(Model model, @PathVariable String activationCode) {
//        Response isActivated = customerService.activateCustomer(activationCode);
//
////        if (isActivated != null) {
////            model.addAttribute("message", "User successfully activated");
////        } else {
////            model.addAttribute("message", "Activation code is not found!");
////        }
//
//        return customerService.activateCustomer(activationCode);
//    }
//}
