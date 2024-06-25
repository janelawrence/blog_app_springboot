package com.springboot.blog;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Blog App REST APIs",
                description = "Blog App REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Jane Zhang",
                        email = "zhang2752@wisc.edu",
                        url = "zhang2752@wisc.edu"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.javaguides.net/license"
                )
        )

)
//public class SpringbootBlogRestApiApplication implements CommandLineRunner {
public class SpringbootBlogRestApiApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    private static Environment environment;

    public static void main(String[] args) {

        SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
        System.out.println("Current Database URL: " + environment.getProperty("spring.datasource.url"));
        System.out.println("Current Database User: " + environment.getProperty("spring.datasource.username"));
        System.out.println("Current Database pw: " + environment.getProperty("spring.datasource.password"));

    }

    @Autowired
    private RoleRepository roleRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        Role adminRole = new Role();
//        adminRole.setName("ROLE_ADMIN");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setName("ROLE_USER");
//        roleRepository.save(userRole);
//
//    }
}
