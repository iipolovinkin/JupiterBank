package ru.blogspot.feomatr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@ManyToOne()
	private Client owner;
	private Long balance;

	public Account() {
		super();
	}

	public Account(Long id, Client owner, Long balance) {
		super();
		this.id = id;
		this.owner = owner;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", owner=" + owner + ", balance="
				+ balance + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getOwner() {
		return owner;
	}

	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
