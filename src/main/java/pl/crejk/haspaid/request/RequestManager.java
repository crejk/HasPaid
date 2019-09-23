package pl.crejk.haspaid.request;

import org.springframework.stereotype.Component;
import pl.crejk.haspaid.HasPaidConstants;
import pl.crejk.haspaid.mojang.MojangCaller;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RequestManager {

    private final Set<String> requests;
    private final MojangCaller caller;

    public RequestManager() {
        this.requests = new LinkedHashSet<>();
        this.caller = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(HasPaidConstants.GSON))
                .baseUrl(HasPaidConstants.MOJANG_API_URL)
                .build()
                .create(MojangCaller.class);
    }

    public void addRequest(String name) {
        this.requests.add(name);
    }

    public Set<String> getCurrentRequests() {
        final Set<String> currentRequests = this.requests.stream()
                .limit(100)
                .collect(Collectors.toSet());

        this.requests.removeAll(currentRequests);

        return currentRequests;
    }

    public MojangCaller getCaller() {
        return this.caller;
    }
}
