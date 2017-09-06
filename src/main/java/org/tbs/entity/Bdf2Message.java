package org.tbs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * bdf2_message:
 */
@Entity
@Table(name = "bdf2_message")
public class Bdf2Message implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID_:
	 */
	private String id;

	/**
	 * CONTENT_:
	 */
	private String content;

	/**
	 * READ_:
	 */
	private boolean read;

	/**
	 * RECEIVER_:
	 */
	private String receiver;

	/**
	 * REPLY_:
	 */
	private boolean reply;

	/**
	 * SEND_DATE_:
	 */
	private Date sendDate;

	/**
	 * SENDER_:
	 */
	private String sender;

	/**
	 * TAGS_:
	 */
	private String tags;

	/**
	 * TITLE_:
	 */
	private String title;

	public Bdf2Message() {
		super();
	}

	public Bdf2Message(String id, String content, boolean read,
			String receiver, boolean reply, Date sendDate, String sender,
			String tags, String title) {
		super();
		this.id = id;
		this.content = content;
		this.read = read;
		this.receiver = receiver;
		this.reply = reply;
		this.sendDate = sendDate;
		this.sender = sender;
		this.tags = tags;
		this.title = title;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(name = "ID_", length = 60, nullable = false)
	public String getId() {
		return id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CONTENT_", length = 1000, nullable = false)
	public String getContent() {
		return content;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	@Column(name = "READ_", nullable = false)
	public boolean isRead() {
		return read;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "RECEIVER_", length = 60, nullable = false)
	public String getReceiver() {
		return receiver;
	}

	public void setReply(boolean reply) {
		this.reply = reply;
	}

	@Column(name = "REPLY_")
	public boolean isReply() {
		return reply;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEND_DATE_")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "SENDER_", length = 60, nullable = false)
	public String getSender() {
		return sender;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "TAGS_", length = 100)
	public String getTags() {
		return tags;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE_", length = 60, nullable = false)
	public String getTitle() {
		return title;
	}

	public String toString() {
		return "Bdf2Message [id=" + id + ",content=" + content + ",read="
				+ read + ",receiver=" + receiver + ",reply=" + reply
				+ ",sendDate=" + sendDate + ",sender=" + sender + ",tags="
				+ tags + ",title=" + title + "]";
	}

}
