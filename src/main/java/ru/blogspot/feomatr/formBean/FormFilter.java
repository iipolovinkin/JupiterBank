package ru.blogspot.feomatr.formBean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author iipolovinkin
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FormFilter {
    private Long idFrom;
    private Long idTo;
    private String startTime = "";
    private String endTime = "";
}
