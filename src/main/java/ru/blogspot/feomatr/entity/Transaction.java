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
