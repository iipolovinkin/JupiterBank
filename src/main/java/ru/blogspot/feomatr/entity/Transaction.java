package ru.blogspot.feomatr.entity;

import lombok.*;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Account transactions.
 *
 * @author iipolovinkin
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Transaction implements Serializable {

    private static final long serialVersionUID = -3193397225071721745L;
    private Long id;
    private BigDecimal amount;
    private Account sender;
    private Account receiver;
    private DateTime time;

    /**
     * @param amount
     * @param sender
     * @param receiver
     * @param time
     */
    public Transaction(BigDecimal amount, Account sender, Account receiver, DateTime time) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
    }

    public Transaction(Long id, BigDecimal amount, Account sender, Account receiver) {
        this(id, amount, sender, receiver, new DateTime());
    }

    public Transaction(Long id, Long amount, Account sender, Account receiver) {
        this(id, new BigDecimal(amount), sender, receiver, new DateTime());
    }

    public Transaction(BigDecimal amount, Account sender, Account receiver) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.time = new DateTime();
    }

}
