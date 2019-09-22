package pl.crejk.haspaid.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import pl.crejk.haspaid.profile.ProfileManager;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Service
public class RequestService {

    private final RequestManager requestManager;
    private final ProfileManager profileManager;
    private final TaskScheduler taskScheduler;

    @Autowired
    public RequestService(RequestManager requestManager, ProfileManager profileManager, TaskScheduler taskScheduler) {
        this.requestManager = requestManager;
        this.profileManager = profileManager;
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    public void run() {
        this.taskScheduler.scheduleAtFixedRate(new RequestTask(this.requestManager, this.profileManager),
                Duration.ofSeconds(1));
    }
}
