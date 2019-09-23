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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

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
        final Mono<Boolean> hasPaid = Mono.fromCallable(() -> this.profileManager.getProfile(name))
                .switchIfEmpty(this.profileRepository.findByName(name)
                        .map(Mono::just).orElseGet(Mono::empty)
                        .doOnNext(this.profileManager::addProfile))
                .switchIfEmpty(this.waitForProfile(name))
                .map(Profile::isPremium);

        return ResponseEntity.ok(hasPaid);
    }

    private Mono<Profile> waitForProfile(String name) {
        this.requestManager.addRequest(name);

        return Flux.interval(Duration.ofSeconds(1))
                .map(it -> this.profileManager.getProfile(name))
                .retry(10, Objects::nonNull)
                .take(1)
                .single();
    }
}
