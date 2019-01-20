package com.hk.message.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
class Message implements Serializable {

    /**
     * 消息Id
     */
    private String id;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 发送者
     */
    private String senderName;

    /**
     * 发送时间
     */
    private LocalDateTime senderDate;

    /**
     * 消息附件
     */
    @Data
    static class MessageAttachment implements Serializable {

        /**
         * 附件id
         */
        private String attachmentId;

        /**
         * 附件路径
         */
        private String attachmentPath;

        /**
         * 附件名
         */
        private String attachmentName;

    }

}
