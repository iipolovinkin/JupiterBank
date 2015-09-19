package ru.blogspot.feomatr.entity;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author iipolvinkin
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString(exclude = "accounts")
@EqualsAndHashCode(exclude = "accounts")
public class Client implements Serializable {
    private static final long serialVersionUID = -9162969037731987965L;

    private Long id;
    @Size(min = 3, max = 30, message = "{client.firstname.size}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{client.firstname.pattern}")
    private String firstname;
    @Size(min = 10, max = 100, message = "{client.address.size}")
    private String address;
    @NotNull(message = "{client.age.notnull}")
    @Min(value = 1, message = "{client.age.lessThanValue}")
    @Max(value = 150, message = "{client.age.greaterThanValue}")
    private Integer age;

    public Client(Long id, String firstname, String address, Integer age) {
        this.id = id;
        this.firstname = firstname;
        this.address = address;
        this.age = age;
    }

    public Client(String firstname, String address, Integer age) {
        this.firstname = firstname;
        this.address = address;
        this.age = age;
    }

    private Set<Account> accounts = new HashSet<>();

}
