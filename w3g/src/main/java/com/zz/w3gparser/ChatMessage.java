package com.zz.w3gparser;

/**
 * Created by dell on 2018/1/5.
 */
public class ChatMessage {
    /**
     * 发送者
     */
    private Player from;

    /**
     * 发送方式
     * 0:发送给所有玩家
     * 1:发送给队友
     * 2:发送给裁判或观看者
     * 3+N:发送给指定玩家
     */
    private long mode;

    /**
     * 接收者（mode为3+N时有效）
     */
    private Player to;

    /**
     * 消息发送时间
     */
    private long time;

    /**
     * 消息内容
     */
    private String message;

    public void setFrom(Player from) {
        this.from = from;
    }

    public void setMode(long mode) {
        this.mode = mode;
    }

    public void setTo(Player to) {
        this.to = to;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Player getFrom() {

        return from;
    }

    public long getMode() {
        return mode;
    }

    public Player getTo() {
        return to;
    }

    public long getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}
