package pl.agh.kis.soa.catering.server.api;

import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.Subscription;

import java.util.List;

public interface ISubscriptionRepository {
    List<Subscription> getAllClientSubscriptions(Long clientId);
    void addSubscription(List<MenuItem> menuItems, Long clientId, String daysOfTheWeek);
    void deleteSubscription(Long subscriptionId);
}
