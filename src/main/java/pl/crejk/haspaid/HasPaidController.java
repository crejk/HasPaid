package pl.crejk.haspaid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

@RestController
public class HasPaidController {

    private final HasPaidManager manager;

    @Autowired
    public HasPaidController(HasPaidManager manager) {
        this.manager = manager;
    }

    @GetMapping("/haspaid")
    public ResponseEntity<Mono<Boolean>> hasPaid(@RequestParam String name) {
        final Boolean cache = this.manager.getResult(name);

        if (cache != null) {
            return ResponseEntity.ok(Mono.just(cache));
        }

        this.manager.addRequest(name);

        final Mono<Boolean> result = Flux.interval(Duration.ofSeconds(1))
                .map(it -> this.manager.getResult(name))
                .retry(Objects::nonNull)
                .take(1)
                .single();

        return ResponseEntity.ok(result);
    }
}
