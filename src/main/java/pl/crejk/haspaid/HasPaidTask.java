package pl.crejk.haspaid;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HasPaidTask implements Runnable {

    private final HasPaidManager manager;

    HasPaidTask(HasPaidManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        final List<String> requests = this.manager.getCurrentRequests();

        if (requests.isEmpty()) {
            return;
        }

        try {
            final Set<HasPaidResult> response = this.manager.getCaller()
                    .profile(requests)
                    .execute()
                    .body();

            if (response == null) {
                return;
            }

            final Set<String> results = response
                    .stream()
                    .map(HasPaidResult::getName)
                    .collect(Collectors.toSet());

            for (String request : requests) {
                if (results.contains(request)) {
                    this.manager.addResult(request, true);
                } else {
                    this.manager.addResult(request, false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
