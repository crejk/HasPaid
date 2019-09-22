package pl.crejk.haspaid.request;

import pl.crejk.haspaid.mojang.MojangProfile;
import pl.crejk.haspaid.profile.Profile;
import pl.crejk.haspaid.profile.ProfileManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RequestTask implements Runnable {

    private final RequestManager requestManager;
    private final ProfileManager profileManager;

    RequestTask(RequestManager requestManager, ProfileManager profileManager) {
        this.requestManager = requestManager;
        this.profileManager = profileManager;
    }

    @Override
    public void run() {
        final List<String> requests = this.requestManager.getCurrentRequests();

        if (requests.isEmpty()) {
            return;
        }

        try {
            final Set<MojangProfile> response = this.requestManager.getCaller()
                    .profile(requests)
                    .execute()
                    .body();

            if (response == null) {
                return;
            }

            final Map<String, MojangProfile> profiles = response.stream()
                    .collect(Collectors.toMap(MojangProfile::getName, profile -> profile));

            for (String name : requests) {
                final MojangProfile mojangProfile = profiles.get(name);

                if (mojangProfile != null) {
                    this.profileManager.addProfile(new Profile(mojangProfile));
                } else {
                    this.profileManager.addProfile(new Profile(name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
