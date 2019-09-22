package pl.crejk.haspaid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.crejk.haspaid.profile.ProfileManager;
import pl.crejk.haspaid.request.RequestManager;

@Configuration
@EnableAsync
@EnableScheduling
public class HasPaidConfiguration {

    @Bean
    public RequestManager manager() {
        return new RequestManager();
    }

    @Bean
    public ProfileManager profileManager() {
        return new ProfileManager();
    }
}
