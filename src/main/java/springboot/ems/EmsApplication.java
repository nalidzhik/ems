package springboot.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableAspectJAutoProxy
@CrossOrigin(origins = "http://localhost:4200")
public class EmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}

}
