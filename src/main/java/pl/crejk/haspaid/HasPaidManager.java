package pl.crejk.haspaid;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class HasPaidManager {

    private final Set<String> requests;
    private final Cache<String, Boolean> results;
    private final HasPaidCaller caller;

    public HasPaidManager() {
        this.requests = new LinkedHashSet<>();
        this.results = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
        this.caller = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(HasPaidConstants.GSON))
                .baseUrl(HasPaidConstants.MOJANG_API_URL)
                .build()
                .create(HasPaidCaller.class);
    }

    public void addRequest(String name) {
        this.requests.add(name);
    }

    public List<String> getCurrentRequests() {
        final List<String> requests = new ArrayList<>(this.requests);
        final int size = requests.size();
        final List<String> currentRequests = requests.subList(0, (size > 100) ? 100 : size);
        this.requests.removeAll(currentRequests);

        return currentRequests;
    }

    public HasPaidCaller getCaller() {
        return this.caller;
    }

    public Boolean getResult(String name) {
        return this.results.getIfPresent(name);
    }

    public void addResult(String name, boolean premium) {
        this.results.put(name, premium);
    }
}
