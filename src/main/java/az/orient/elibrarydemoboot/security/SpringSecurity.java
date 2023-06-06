//package az.orient.elibrarydemoboot.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SpringSecurity extends WebSecurityConfigurerAdapter {
//
//    private final MyUserDetailsService myUserDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //autentifikasiya ucun
//        //auth.inMemoryAuthentication().withUser("seva").password("seva123").roles("ADMIN")
//                //.and().withUser("fuad").password("12345").roles("USER");
//        auth.userDetailsService(myUserDetailsService);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();//csrf -
//        http.authorizeRequests().antMatchers("/user/**").permitAll()
//                .antMatchers( "/customer/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/payment/**").hasRole("ADMIN").anyRequest().authenticated().and().httpBasic();
//
//    }
//
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        //return NoOpPasswordEncoder.getInstance(); //password'u encode etmemek ucun
//        return new BCryptPasswordEncoder(); //parolu yoxlayanda sifreliyib yoxlayacaq ve bazaya gonderecek
//    }
//}
