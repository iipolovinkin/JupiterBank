package ru.blogspot.feomatr.entity;


import lombok.*;

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

    private Long accountFrom;
    private Long accountTo;
    private BigDecimal amount;
    private String dateTime;

}
