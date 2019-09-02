package com.hk.quartz.enums;

import lombok.Getter;

/**
 * @author kevin
 * @date 2019-8-16 16:36
 */
public enum QuartzJobStatus {

    DELETE((byte) 0, "已删除", "red"),

    START((byte) 1, "已启用", "blue"),

    STOP((byte) 2, "已停止", "gray");

    @Getter
    private byte state;

    @Getter
    private String text;

    @Getter
    private String color;

    QuartzJobStatus(byte state, String text, String color) {
        this.state = state;
        this.text = text;
        this.color = color;
    }

    public static String getText(byte state) {
        for (QuartzJobStatus status : values()) {
            if (status.state == state) {
                return status.getText();
            }
        }
        return null;
    }

    public static String getColor(byte state) {
        for (QuartzJobStatus status : values()) {
            if (status.state == state) {
                return status.getColor();
            }
        }
        return null;
    }
}
