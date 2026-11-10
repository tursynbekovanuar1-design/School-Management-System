package school.notification;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Subject: keeps the list of observers and notifies them about events.
 * CopyOnWriteArrayList makes it safe to subscribe/unsubscribe while notifying.
 *
 * Pattern: Observer (Subject)
 * Owner:   Developer 2
 */
public class GradeEventPublisher {

    private final List<GradeObserver> observers = new CopyOnWriteArrayList<>();

    public void subscribe(GradeObserver observer) {
        observers.add(Objects.requireNonNull(observer, "observer"));
    }

    public void unsubscribe(GradeObserver observer) {
        observers.remove(observer);
    }

    public void publish(GradeEvent event) {
        for (GradeObserver observer : observers) {
            observer.update(event);
        }
    }
}
