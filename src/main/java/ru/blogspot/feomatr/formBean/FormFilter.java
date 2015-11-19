package ru.blogspot.feomatr.formBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author iipolovinkin
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FormFilter {
	public static DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("dd.MM.yyyy");

	private String senderAccountNo;
	private String receiverAccountNo;
	private String startTime = "";
	private String endTime = "";

	public FormFilter(String senderAccountNo, String receiverAccountNo, DateTime startTime, DateTime endTime) {
		this.senderAccountNo = senderAccountNo;
		this.receiverAccountNo = receiverAccountNo;
		this.startTime = startTime == null ? "" : startTime.toString(DATE_FORMAT);
		this.endTime = endTime == null ? "" : endTime.toString(DATE_FORMAT);
	}
}
