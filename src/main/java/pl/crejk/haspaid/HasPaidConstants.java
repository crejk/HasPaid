package pl.crejk.haspaid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class HasPaidConstants {

    private HasPaidConstants() {
    }

    public static final String MOJANG_API_URL = "https://api.mojang.com";

    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
}
