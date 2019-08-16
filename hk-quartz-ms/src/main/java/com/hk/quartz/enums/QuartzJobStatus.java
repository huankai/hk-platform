package com.hk.quartz.enums;

import lombok.Getter;

/**
 * @author kevin
 * @date 2019-8-16 16:36
 */
public enum QuartzJobStatus {

    DELETE((byte) 0, "已删除"),

    START((byte) 1, "已启用"),

    STOP((byte) 2, "已停止");

    @Getter
    private byte state;

    @Getter
    private String text;

    QuartzJobStatus(byte state, String text) {
        this.state = state;
        this.text = text;
    }

    public static String getText(byte state) {
        for (QuartzJobStatus status : values()) {
            if (status.state == state) {
                return status.getText();
            }
        }
        return null;
    }
}
