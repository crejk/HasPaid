package pl.crejk.haspaid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.crejk.haspaid.profile.Profile;
import pl.crejk.haspaid.profile.ProfileManager;
import pl.crejk.haspaid.profile.ProfileRepository;
import pl.crejk.haspaid.request.RequestManager;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class HasPaidController {

    private final RequestManager requestManager;
    private final ProfileManager profileManager;
    private final ProfileRepository profileRepository;

    @Autowired
    public HasPaidController(RequestManager requestManager, ProfileManager profileManager, ProfileRepository profileRepository) {
        this.requestManager = requestManager;
        this.profileManager = profileManager;
        this.profileRepository = profileRepository;
    }

    @GetMapping("/haspaid")
    public ResponseEntity<Mono<Boolean>> hasPaid(@RequestParam String name) {
        final Mono<Boolean> result = this.profileManager.getProfile(name)
                .orElse(() -> this.profileRepository.findByName(name))
                .map(Mono::just)
                .getOrElse(() -> this.waitForProfile(name))
                .map(Profile::isPremium);

        return ResponseEntity.ok(result);
    }

    private Mono<Profile> waitForProfile(String name) {
        this.requestManager.addRequest(name);

        return Mono.delay(Duration.ofSeconds(1))
                .flatMap(it -> this.profileManager.getProfile(name)
                        .map(Mono::just).getOrElse(Mono::empty))
                .take(Duration.ofSeconds(10));
    }
}
