package ru.blogspot.feomatr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import javax.validation.constraints.Min;

/**
 * @author iipolvinkin
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "owner")
@EqualsAndHashCode(exclude = "owner")
@AllArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Client owner;
	@Min(value = 0, message = "Balance can not be less then zero")
	private Long balance = 0L;

	public Account(Client owner) {
		this.owner = owner;
	}

	public Account(Client owner, Long balance) {
		this.owner = owner;
		this.balance = balance;
	}
}
