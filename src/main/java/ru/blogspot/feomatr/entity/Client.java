/**
 *
 */
package ru.blogspot.feomatr.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author iipolvinkin
 *
 */
@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = -9162969037731987965L;

	@Id
	private Long id;
	private String name;
	private String address;
	private Integer age;

	public Client() {

	}

	public Client(Long id, String name, String address, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
	}

	public Client(String name, String address, Integer age) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
	}

	@OneToMany
	private Set<Account> accounts = new HashSet<>();

	@Override
	public String toString() {
		return "Client{" + "id=" + id + ", name=" + name + ", address="
				+ address + ", age=" + age + '}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
