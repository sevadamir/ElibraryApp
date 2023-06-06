package az.orient.elibrarydemoboot;


import az.orient.elibrarydemoboot.entity.Users;
//import az.orient.elibrarydemoboot.security.MyUserDetailsService;
import az.orient.elibrarydemoboot.schedule.MyThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableScheduling
public class ElibrarydemobootApplication {
	public static void main(String[] args)  {
		//Users users = new Users();
		//MyUserDetailsService myUserDetailsService = new MyUserDetailsService(users);
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		System.out.println(passwordEncoder.encode("seva123"));
//		System.out.println(passwordEncoder.encode("fuad123"));
//		System.out.println(passwordEncoder.encode("leyla123"));
//		MyThread myThread = new MyThread();
//		myThread.start(); // run metodunu ishe salir
		SpringApplication.run(ElibrarydemobootApplication.class, args);
	}

}
