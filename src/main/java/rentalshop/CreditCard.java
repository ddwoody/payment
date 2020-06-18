package rentalshop;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="CreditCard_table")
public class CreditCard {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String cardNo;
    private String name;
    private Long chargeAmount;
    private Long rentId;
    private String result;

    @PostPersist
    public void onPostPersist(){
        CardSettled cardSettled = new CardSettled();
        BeanUtils.copyProperties(this, cardSettled);
        cardSettled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Long chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }




}
