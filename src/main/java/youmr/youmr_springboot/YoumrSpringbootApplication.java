package youmr.youmr_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YoumrSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoumrSpringbootApplication.class, args);
    }

}
