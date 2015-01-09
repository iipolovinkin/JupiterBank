/**
 *
 */
package ru.blogspot.feomatr.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author iipolvinkin
 */
public class Client implements Serializable {

    private static final long serialVersionUID = -9162969037731987965L;

    private Long id;
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Name must be alphanumeric with no spaces.")
    private String firstname;
    @Size(min = 10, max = 50, message = "Address must be between 10 and 50 characters long.")
    private String address;
    @NotNull(message = "Age should not be empty.")
    @Min(1)
    @Max(value = 150, message = "Age can not be more than 150")
    private Integer age;

    public Client() {

    }

    public Client(Long id, String firstname, String address, Integer age) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.address = address;
        this.age = age;
    }

    public Client(String firstname, String address, Integer age) {
        super();
        this.firstname = firstname;
        this.address = address;
        this.age = age;
    }

    private Set<Account> accounts = new HashSet<>();

    public String toString() {
        return "Client{" + "id=" + id + ", name=" + firstname + ", address="
                + address + ", age=" + age + '}';
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
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Client other = (Client) obj;
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }
        if (age == null) {
            if (other.age != null) {
                return false;
            }
        } else if (!age.equals(other.age)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (firstname == null) {
            if (other.firstname != null) {
                return false;
            }
        } else if (!firstname.equals(other.firstname)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    /**
     * @return the accounts
     */
    public Set<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

}
