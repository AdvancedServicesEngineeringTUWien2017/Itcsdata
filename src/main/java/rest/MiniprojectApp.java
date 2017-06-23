package rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MiniprojectApp {
    public static void main(String... args) {
        SpringApplication.run(MiniprojectApp.class, args);
    }
}
