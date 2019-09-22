package pl.crejk.haspaid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

final class HasPaidConstants {

    private HasPaidConstants() {
    }

    static final String MOJANG_API_URL = "https://api.mojang.com";

    static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
}
