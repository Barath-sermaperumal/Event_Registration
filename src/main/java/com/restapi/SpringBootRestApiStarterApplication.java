package com.restapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableSwagger2
public class SpringBootRestApiStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiStarterApplication.class, args);
	}

}


