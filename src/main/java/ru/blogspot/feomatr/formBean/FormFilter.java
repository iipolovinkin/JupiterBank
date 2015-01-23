/**
 *
 */
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
    Long idFrom;
    Long idTo;
    String startTime = "";
    String endTime = "";
}
