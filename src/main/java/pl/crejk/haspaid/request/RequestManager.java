package pl.crejk.haspaid.request;

import io.vavr.collection.HashSet;
import org.springframework.stereotype.Component;
import pl.crejk.haspaid.HasPaidConstants;
import pl.crejk.haspaid.mojang.MojangCaller;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.LinkedHashSet;
import java.util.Set;

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

    public io.vavr.collection.Set<String> getCurrentRequests() {
        return this.requests.stream()
                .limit(100)
                .collect(HashSet.collector())
                .peek(this.requests::remove);
    }

    public MojangCaller getCaller() {
        return this.caller;
    }
}
