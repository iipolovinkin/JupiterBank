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
	private Account receiver;
	private DateTime time;

	/**
	 * @param amount
	 * @param sender
	 * @param receiver
	 * @param time
	 */
	public Transaction(Long amount, Account sender, Account receiver,
			DateTime time) {
		super();
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
		this.time = time;
	}

	/**
	 * @param id
	 * @param amount
	 * @param sender
	 * @param receiver
	 * @param time
	 */
	public Transaction(Long id, Long amount, Account sender, Account receiver,
			DateTime time) {
		super();
		this.id = id;
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
		this.time = time;
	}

	/**
	 * @param id
	 * @param amount
	 * @param sender
	 * @param receiver
	 */
	public Transaction(Long id, Long amount, Account sender, Account receiver) {
		this(id, amount, sender, receiver, new DateTime());
	}

	/**
	 * @param amount
	 * @param sender
	 * @param receiver
	 */
	public Transaction(Long amount, Account sender, Account receiver) {
		super();
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
		this.time = new DateTime();
	}

	public Transaction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", sender="
				+ sender + ", receiver=" + receiver + ", time=" + time + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
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

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

}
