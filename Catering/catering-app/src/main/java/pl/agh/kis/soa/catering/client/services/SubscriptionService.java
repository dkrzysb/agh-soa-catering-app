package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.ISubscriptionRepository;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.Subscription;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class SubscriptionService {
    @EJB(lookup = "java:global/catering-business-logic/SubscriptionRepository")
    ISubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllClientSubscriptions(Long clientId) {
        return subscriptionRepository.getAllClientSubscriptions(clientId);
    }

    public void addSubscription(List<MenuItem> menuItems, Long clientId, String daysOfTheWeek) {
        subscriptionRepository.addSubscription(menuItems, clientId, daysOfTheWeek);
    }

    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteSubscription(subscriptionId);
    }
}
