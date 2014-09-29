/**
 * 
 */
package ru.blogspot.feomatr.entity;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * Account transactions.
 * 
 * @author iipolovinkin
 *
 */
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long amount;
	private Account sender;
	private Account reciver;
	private DateTime time;

	/**
	 * @param id
	 * @param amount
	 * @param sender
	 * @param reciver
	 * @param time
	 */
	public Transaction(Long id, Long amount, Account sender, Account reciver,
			DateTime time) {
		super();
		this.id = id;
		this.amount = amount;
		this.sender = sender;
		this.reciver = reciver;
		this.time = time;
	}

	/**
	 * @param id
	 * @param amount
	 * @param sender
	 * @param reciver
	 */
	public Transaction(Long id, Long amount, Account sender, Account reciver) {
		this(id, amount, sender, reciver, new DateTime());
	}

	public Transaction() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", sender="
				+ sender + ", reciver=" + reciver + ", time=" + time + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reciver == null) ? 0 : reciver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reciver == null) {
			if (other.reciver != null)
				return false;
		} else if (!reciver.equals(other.reciver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getReciver() {
		return reciver;
	}

	public void setReciver(Account reciver) {
		this.reciver = reciver;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

}
