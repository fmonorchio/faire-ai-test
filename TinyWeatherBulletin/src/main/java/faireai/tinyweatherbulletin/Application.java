package faireai.tinyweatherbulletin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = "faireai")
@OpenAPIDefinition(
		info = @Info(
				title = "Tiny Weather Bulletin",
				description = "A simple application to retrieve weather for any city in the world",
				version = "0.0.1",
				contact = @Contact(
						name = "Francesco Monorchio",
						email = "francesco.monorchio@yahoo.it",
						url = "http://localhost:8080/docs/faire-ai-case-study.pdf"
				)

		),
		tags = @Tag(name = "Weather")
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
