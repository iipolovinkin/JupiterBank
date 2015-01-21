/**
 *
 */
package ru.blogspot.feomatr.entity;


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
public class Broker {

    Long accountFrom;
    Long accountTo;
    BigDecimal amount;

}
