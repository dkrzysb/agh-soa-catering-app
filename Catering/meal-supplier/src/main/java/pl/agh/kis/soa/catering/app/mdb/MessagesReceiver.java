package pl.agh.kis.soa.catering.app.mdb;

import pl.agh.kis.soa.catering.server.api.IShippingOrderRepository;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "CateringQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination",
                propertyValue = "CateringQueue")
})
public class MessagesReceiver implements MessageListener {
    @EJB(lookup = "java:global/catering-business-logic/ShippingOrderRepository")
    IShippingOrderRepository shippingOrderRepository;

    @Override
    public void onMessage(Message msg) {
        TextMessage txtMsg = null;
        try {
            if (msg instanceof TextMessage) {
                txtMsg = (TextMessage) msg;
                String txt = txtMsg.getText();

                Long orderId = Long.parseLong(txt);
                shippingOrderRepository.addShippingOrder(orderId);
            }
        } catch (JMSException ex) {
            System.err.println("Error receiving message: " + ex.getMessage());
        }
    }
}