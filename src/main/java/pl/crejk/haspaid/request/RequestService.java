package pl.crejk.haspaid.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import pl.crejk.haspaid.profile.ProfileManager;
import pl.crejk.haspaid.profile.ProfileRepository;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Service
public class RequestService {

    private final RequestManager requestManager;
    private final ProfileManager profileManager;
    private final ProfileRepository profileRepository;
    private final TaskScheduler taskScheduler;

    @Autowired
    public RequestService(RequestManager requestManager, ProfileManager profileManager, ProfileRepository profileRepository, TaskScheduler taskScheduler) {
        this.requestManager = requestManager;
        this.profileManager = profileManager;
        this.profileRepository = profileRepository;
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    public void run() {
        this.taskScheduler.scheduleAtFixedRate(new RequestTask(this.requestManager, this.profileManager, this.profileRepository),
                Duration.ofSeconds(1));
    }
}
