package co.psyke.nanosoftma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NanosoftmaApplication {

	public static void main(String[] args) {
		ApplicationContext ctx  = SpringApplication.run(NanosoftmaApplication.class, args);
		System.out.println("List of all loaded beans");
		for (String x : ctx.getBeanDefinitionNames()){
			System.out.println(x);
		}
	}

}
