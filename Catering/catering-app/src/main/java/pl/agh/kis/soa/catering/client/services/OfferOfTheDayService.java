package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.IOfferOfTheDayRepository;
import pl.agh.kis.soa.catering.server.model.OfferOfTheDay;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean(name = "offerOfTheDayService")
@ApplicationScoped
public class OfferOfTheDayService {
    @EJB(lookup = "java:global/catering-business-logic/OfferOfTheDayRepository")
    IOfferOfTheDayRepository offerOfTheDayRepository;

    public void addOfferOfTheDay(Long menuItemId, BigDecimal price) {
        offerOfTheDayRepository.addOfferOfTheDay(menuItemId, price);
    }

    public OfferOfTheDay getOfferOfTheDay(Long menuItemId) {
        return offerOfTheDayRepository.getOfferOfTheDay(menuItemId);
    }

    public void deleteOfferOfTheDay(Long offerOfTheDayId) {
        offerOfTheDayRepository.deleteOfferOfTheDay(offerOfTheDayId);
    }

    public List<OfferOfTheDay> getAllOffersOfTheDay() {
        return offerOfTheDayRepository.getAllOffersOfTheDay();
    }

    public boolean isMenuItemInOffersOfTheDay(Long menuItemId) { return offerOfTheDayRepository.isMenuItemInOffersOfTheDay(menuItemId); }
}
