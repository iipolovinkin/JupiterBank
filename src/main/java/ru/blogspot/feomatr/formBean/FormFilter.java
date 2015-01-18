/**
 *
 */
package ru.blogspot.feomatr.formBean;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author iipolovinkin
 *
 */
public class FormFilter {
	Long idFrom, idTo;
	String startTime = "";
	String endTime = "";

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	DateTime time;
	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

	public FormFilter() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FormFilter [idFrom=" + idFrom + ", idTo=" + idTo
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	/**
	 * @return the idFrom
	 */
	public Long getIdFrom() {
		return idFrom;
	}

	/**
	 * @param idFrom
	 *            the idFrom to set
	 */
	public void setIdFrom(Long idFrom) {
		this.idFrom = idFrom;
	}

	/**
	 * @return the idTo
	 */
	public Long getIdTo() {
		return idTo;
	}

	/**
	 * @param idTo
	 *            the idTo to set
	 */
	public void setIdTo(Long idTo) {
		this.idTo = idTo;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	};
}
