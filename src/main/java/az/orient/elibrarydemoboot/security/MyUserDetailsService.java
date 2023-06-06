//package az.orient.elibrarydemoboot.security;
//
//import az.orient.elibrarydemoboot.dto.response.RespStatus;
//import az.orient.elibrarydemoboot.dto.response.Response;
//import az.orient.elibrarydemoboot.entity.Users;
//import az.orient.elibrarydemoboot.enums.EnumAvailableStatus;
//import az.orient.elibrarydemoboot.exception.ExceptionConstants;
//import az.orient.elibrarydemoboot.exception.LibraryException;
//import az.orient.elibrarydemoboot.repository.UsersRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class MyUserDetailsService implements UserDetailsService {
//
//    private final UsersRepository usersRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users user = usersRepository.findUsersByEmailAndActive(username, EnumAvailableStatus.ACTIVE.value);
//        if (user == null) {
//            throw new UsernameNotFoundException("Not found: " + username);
//        }
//        MyUserDetails myUserDetails = new MyUserDetails(user);
//        return myUserDetails;
//    }
//
//    public void encodePassword(Users user) {
//        Response response = new Response<>();
//        try {
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String username = user.getEmail();
//            String password = user.getPassword();
//            Users users = usersRepository.findUsersByEmailAndActive(username, EnumAvailableStatus.ACTIVE.value);
//            if (users == null) {
//                throw new LibraryException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid requeat data");
//            }
//            user.setPassword(passwordEncoder.encode(password));
//            usersRepository.save(user);
//        } catch (LibraryException ex) {
//            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
//            ex.printStackTrace();
//        } catch (Exception ex) {
//            response.setStatus(new RespStatus(ExceptionConstants.ITERNAL_EXCEPTION, ex.getMessage()));
//            ex.printStackTrace();
//        }
//    }
//}
