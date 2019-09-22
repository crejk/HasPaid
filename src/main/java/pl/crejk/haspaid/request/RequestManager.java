package pl.crejk.haspaid.request;

import pl.crejk.haspaid.HasPaidConstants;
import pl.crejk.haspaid.mojang.MojangCaller;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    public List<String> getCurrentRequests() {
        final List<String> requests = new ArrayList<>(this.requests);
        final int size = requests.size();
        final List<String> currentRequests = requests.subList(0, (size > 100) ? 100 : size);
        this.requests.removeAll(currentRequests);

        return currentRequests;
    }

    public MojangCaller getCaller() {
        return this.caller;
    }
}
