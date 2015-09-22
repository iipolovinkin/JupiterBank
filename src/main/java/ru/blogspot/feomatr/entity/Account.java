package ru.blogspot.feomatr.entity;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author iipolvinkin
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString(exclude = "owner")
@EqualsAndHashCode(exclude = "owner")
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @XStreamOmitField
    private Client owner;
    @Min(value = 0, message = "{account.balance.min}")
    @Max(value = 10000000, message = "{account.balance.max}")
    private BigDecimal balance;

    public Account(Client owner) {
        this(owner, new BigDecimal(0));
    }

    public Account(Client owner, BigDecimal balance) {
        this.owner = owner;
        this.balance = balance;
    }

    /**
     * Constructor for tests
     */
    public Account(Client owner, Long balance) {
        this.owner = owner;
        this.balance = new BigDecimal(balance);
    }

    /**
     * Constructor for tests
     */
    public Account(Long id, Client owner, Long balance) {
        this.id = id;
        this.owner = owner;
        this.balance = new BigDecimal(balance);
    }
}
