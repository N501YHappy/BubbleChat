package xyz.n501yhappy.bubbleChat;

import java.util.UUID;

public class MessageStruct {
    private String message;
    private Long timestamp;
    private UUID armorID;      // 对应的泡泡盔甲架 UUID
    private UUID sender;       // 发送者
    private final UUID structUUID; // 这个结构的唯一ID
    public MessageStruct(String message, UUID sender) {
        this(message, System.currentTimeMillis(), null, sender);
    }


    public MessageStruct(String message, Long timestamp, UUID armorID, UUID sender) {
        this.message = message;
        this.timestamp = timestamp;
        this.armorID = armorID;
        this.sender = sender;
        this.structUUID = UUID.randomUUID();
    }

    public MessageStruct(String message, Long timestamp) {
        this(message, timestamp, null, null);
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

    public UUID getStructUUID() {
        return structUUID;
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

    public boolean hasSender() {
        return sender != null;
    }

    @Override
    public String toString() {
        return "MessageStruct{" +
                "structUUID=" + structUUID +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", armorID=" + armorID +
                ", sender=" + sender +
                '}';
    }
}