package pl.crejk.haspaid;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;
import java.util.Set;

public interface HasPaidCaller {

    @POST("/profiles/minecraft")
    Call<Set<HasPaidResult>> profile(@Body List<String> names);
}
