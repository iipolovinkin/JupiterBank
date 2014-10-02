/**
 * 
 */
package ru.blogspot.feomatr.formBean;

import org.joda.time.DateTime;

/**
 * @author iipolovinkin
 *
 */
public class FormFilter {
	Long idFrom, idTo;
	DateTime startTime, endTime;

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
	public DateTime getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public DateTime getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	};

}
