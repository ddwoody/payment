package rentalshop;

import rentalshop.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    CreditCardRepository creditCardRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBikeReturned_Pay(@Payload BikeReturned bikeReturned){

        if(bikeReturned.isMe()){
            System.out.println("##### listener Pay : " + bikeReturned.toJson());

            CreditCard creditCard = new CreditCard();
            creditCard.setCardNo(bikeReturned.getCardNo());
            creditCard.setName(bikeReturned.getBorrowerName());
            creditCard.setChargeAmount(bikeReturned.getUseAmount());
            creditCard.setResult("PAYED");
            creditCard.setRentId(bikeReturned.getId());
            creditCardRepository.save(creditCard);
        }
    }

}
