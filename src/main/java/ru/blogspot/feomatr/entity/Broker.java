package ru.blogspot.feomatr.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author iipolovinkin
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Broker {

    private String senderAccountNo;
    private String receiverAccountNo;
    private BigDecimal amount;
    private String dateTime;

}
