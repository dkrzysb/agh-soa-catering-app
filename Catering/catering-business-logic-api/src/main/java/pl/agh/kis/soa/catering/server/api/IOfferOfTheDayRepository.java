package pl.agh.kis.soa.catering.server.api;

import pl.agh.kis.soa.catering.server.model.OfferOfTheDay;

import java.math.BigDecimal;
import java.util.List;

public interface IOfferOfTheDayRepository {
    void addOfferOfTheDay(Long menuItemId, BigDecimal price);
    void deleteOfferOfTheDay(Long offerOfTheDayId);
    List<OfferOfTheDay> getAllOffersOfTheDay();
}
