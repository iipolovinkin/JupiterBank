package message.entity;

import lombok.Data;

@Data
public class Contract {
    Customer customer;
    String contractNumber;
    String counterPartyNumber;
}
