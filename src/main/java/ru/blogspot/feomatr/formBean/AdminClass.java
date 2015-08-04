package ru.blogspot.feomatr.formBean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: comment
 *
 * @author iipolovinkin
 * @since 30.03.2015
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminClass {
    private static final Logger log = LoggerFactory.getLogger(AdminClass.class);

    private Integer attr01;
    private Integer attr02;
    private String attr03;
    private String attr04;
    private Integer attr05;
    private Integer attr06;
    private String attr07;
    private String attr08;
    private String attr09;
    private String attr10;

}
