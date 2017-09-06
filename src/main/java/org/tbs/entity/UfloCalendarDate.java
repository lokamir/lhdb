package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * uflo_calendar_date:
 */
@Entity
@Table(name = "uflo_calendar_date")
public class UfloCalendarDate implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private long id;

	/**
	 * CALENDAR_DATE_:
	 */
	private Date calendarDate;

	/**
	 * CALENDAR_ID_:
	 */
	private long calendarId;

	/**
	 * DAY_OF_MONTH_:
	 */
	private int dayOfMonth;

	/**
	 * DAY_OF_WEEK_:
	 */
	private int dayOfWeek;

	/**
	 * MONTH_OF_YEAR_:
	 */
	private int monthOfYear;

	/**
	 * NAME_:
	 */
	private String name;

	/**
	 * RANGE_END_TIME_:
	 */
	private String rangeEndTime;

	/**
	 * RANGE_START_TIME_:
	 */
	private String rangeStartTime;

	public UfloCalendarDate() {
		super();
	}

	public UfloCalendarDate(long id, Date calendarDate, long calendarId,
			int dayOfMonth, int dayOfWeek, int monthOfYear, String name,
			String rangeEndTime, String rangeStartTime) {
		super();
		this.id = id;
		this.calendarDate = calendarDate;
		this.calendarId = calendarId;
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeek = dayOfWeek;
		this.monthOfYear = monthOfYear;
		this.name = name;
		this.rangeEndTime = rangeEndTime;
		this.rangeStartTime = rangeStartTime;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", nullable = false)
	public long getId() {
		return id;
	}

	public void setCalendarDate(Date calendarDate) {
		this.calendarDate = calendarDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CALENDAR_DATE_")
	public Date getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarId(long calendarId) {
		this.calendarId = calendarId;
	}

	@Column(name = "CALENDAR_ID_")
	public long getCalendarId() {
		return calendarId;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	@Column(name = "DAY_OF_MONTH_")
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Column(name = "DAY_OF_WEEK_")
	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	@Column(name = "MONTH_OF_YEAR_")
	public int getMonthOfYear() {
		return monthOfYear;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_", length = 60)
	public String getName() {
		return name;
	}

	public void setRangeEndTime(String rangeEndTime) {
		this.rangeEndTime = rangeEndTime;
	}

	@Column(name = "RANGE_END_TIME_", length = 60)
	public String getRangeEndTime() {
		return rangeEndTime;
	}

	public void setRangeStartTime(String rangeStartTime) {
		this.rangeStartTime = rangeStartTime;
	}

	@Column(name = "RANGE_START_TIME_", length = 60)
	public String getRangeStartTime() {
		return rangeStartTime;
	}

	public String toString() {
		return "UfloCalendarDate [id=" + id + ",calendarDate=" + calendarDate
				+ ",calendarId=" + calendarId + ",dayOfMonth=" + dayOfMonth
				+ ",dayOfWeek=" + dayOfWeek + ",monthOfYear=" + monthOfYear
				+ ",name=" + name + ",rangeEndTime=" + rangeEndTime
				+ ",rangeStartTime=" + rangeStartTime + "]";
	}

}
