/**
 *
 */
package ru.blogspot.feomatr.entity;


/**
 * @author iipolovinkin
 */
public class Broker {

    Long accountFrom, accountTo;
    Long amount;

    /**
     * @return the accountFrom
     */
    public Long getAccountFrom() {
        return accountFrom;
    }

    /**
     * @param accountFrom the accountFrom to set
     */
    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    /**
     * @return the accountTo
     */
    public Long getAccountTo() {
        return accountTo;
    }

    /**
     * @param accountTo the accountTo to set
     */
    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    /**
     * @return the amount
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     *
     */
    public Broker() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Broker [accountFrom=" + accountFrom + ", accountTo="
                + accountTo + ", amount=" + amount + "]";
    }
}
