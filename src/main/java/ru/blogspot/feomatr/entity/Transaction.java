package ru.blogspot.feomatr.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    private String senderAccountNo;
    private String receiverAccountNo;
    private DateTime time;

    /**
     * @param amount
     * @param sender
     * @param receiver
     * @param time
     */
    public Transaction(BigDecimal amount, String sender, String receiver, DateTime time) {
        this.amount = amount;
        this.senderAccountNo = sender;
        this.receiverAccountNo = receiver;
        this.time = time;
    }

    public Transaction(Long id, BigDecimal amount, String sender, String receiver) {
        this(id, amount, sender, receiver, new DateTime());
    }

    public Transaction(Long id, Long amount, String sender, String receiver) {
        this(id, new BigDecimal(amount), sender, receiver, new DateTime());
    }

    public Transaction(BigDecimal amount, String sender, String receiver) {
        this.amount = amount;
        this.senderAccountNo = sender;
        this.receiverAccountNo = receiver;
        this.time = new DateTime();
    }

}
