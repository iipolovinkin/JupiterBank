/**
 *
 */
package ru.blogspot.feomatr.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "accounts")
@EqualsAndHashCode(exclude = "accounts")
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

}
