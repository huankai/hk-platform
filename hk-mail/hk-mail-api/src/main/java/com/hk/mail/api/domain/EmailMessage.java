package com.hk.mail.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文本邮件
 *
 * @author huangkai
 * @date 2019-01-08 17:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class EmailMessage implements Serializable {

	/**
	 * 设置回复人
	 */
	private String replyTo;

	/**
	 * 收件人
	 */
	private String[] to;

	/**
	 * 抄送
	 */
	private String[] cc;

	/**
	 * 密送(不会在邮件收件人名单中显示出来)
	 */
	private String[] bcc;

	/**
	 * 发送时间
	 */
	private Date sentDate;

	/**
	 * 发送主题
	 */
	private String subject;

	/**
	 * 发送内容
	 */
	private String text;

}
