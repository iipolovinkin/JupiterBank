package ru.blogspot.feomatr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@NoArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @XStreamOmitField
    @JsonIgnore
    private Client owner;
    @Min(value = 0, message = "{account.balance.min}")
    @Max(value = 10000000, message = "{account.balance.max}")
    private BigDecimal balance;
    @NonNull
    private String accountNo;

    public Account(Client owner) {
        this(owner, new BigDecimal(0));
    }

    public Account(Client owner, BigDecimal balance) {
        this(null, owner, balance);
    }

    /**
     * Constructor for tests
     */
    public Account(Client owner, Long balance) {
        this(null, owner, balance);
    }

    public Account(Long id, Client owner, BigDecimal balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        this.accountNo = AccountNo.generatePrivateBankAccountNo();
    }

    /**
     * Constructor for tests
     */
    public Account(Long id, Client owner, Long balance) {
        this(id, owner, new BigDecimal(balance));
    }

}
