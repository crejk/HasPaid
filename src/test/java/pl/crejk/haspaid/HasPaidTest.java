package pl.crejk.haspaid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class HasPaidTest {

    @Autowired
    private WebTestClient client;

    @Before
    public void setUp() {
        this.client = this.client
                .mutate()
                .responseTimeout(Duration.ofSeconds(10))
                .build();
    }

    @Test
    public void testPremiumAccount() {
        this.client
                .get()
                .uri("/haspaid?name=crejk")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .isEqualTo(true);
    }

    @Test
    public void testCrackedAccount() {
        this.client
                .get()
                .uri("/haspaid?name=vocan_is_not_cool")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .isEqualTo(false);
    }
}
