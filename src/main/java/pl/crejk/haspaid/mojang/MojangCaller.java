package pl.crejk.haspaid.mojang;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;
import java.util.Set;

public interface MojangCaller {

    @POST("/profiles/minecraft")
    Call<Set<MojangProfile>> profile(@Body List<String> names);
}
