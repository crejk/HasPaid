package pl.crejk.haspaid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
public class HasPaidConfiguration {

    @Bean
    public HasPaidManager manager() {
        return new HasPaidManager();
    }
}
