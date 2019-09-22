package pl.crejk.haspaid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Service
public class HasPaidService {

    private final HasPaidManager manager;
    private final TaskScheduler taskScheduler;

    @Autowired
    public HasPaidService(HasPaidManager manager, TaskScheduler taskScheduler) {
        this.manager = manager;
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    public void run() {
        this.taskScheduler.scheduleAtFixedRate(new HasPaidTask(this.manager), Duration.ofSeconds(1));
    }
}
