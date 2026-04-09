package xyz.n501yhappy.bubbleChat;

import java.util.UUID;

public class MessageStruct {
    private String message;
    private Long timestamp;
    private UUID armorID;  // 对应的泡泡盔甲架 UUID
    private UUID sender; //发送者

    public MessageStruct(String message) {
        this(message, System.currentTimeMillis());
    }

    public MessageStruct(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.armorID = null;  // 默认没有关联的盔甲架
    }

    public MessageStruct(String message, Long timestamp, UUID armorID) {
        this.message = message;
        this.timestamp = timestamp;
        this.armorID = armorID;
    }

    // Getters
    public Long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public UUID getArmorID() {
        return armorID;
    }

    public UUID getSender() {
        return sender;
    }

    // Setters
    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setArmorID(UUID armorID) {
        this.armorID = armorID;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - timestamp > 10000;
    }

    public boolean hasArmorStand() {
        return armorID != null;
    }

    @Override
    public String toString() {
        return "MessageStruct{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", armorID=" + armorID +
                ", sender=" + sender +
                '}';
    }
}