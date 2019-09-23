package pl.crejk.haspaid.profile;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ProfileManager {

    private final Cache<String, Profile> profiles;

    public ProfileManager() {
        this.profiles = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }

    public Profile getProfile(String name) {
        return this.profiles.getIfPresent(name);
    }

    public void addProfile(Profile profile) {
        this.profiles.put(profile.getName(), profile);
    }
}
