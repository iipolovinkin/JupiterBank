/**
 *
 */
package ru.blogspot.feomatr.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    Long amount;

}
