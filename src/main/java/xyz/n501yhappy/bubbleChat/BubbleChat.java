package xyz.n501yhappy.bubbleChat;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.n501yhappy.bubbleChat.listeners.Chatting;
import xyz.n501yhappy.bubbleChat.listeners.Moving;
import xyz.n501yhappy.bubbleChat.utils.ChattingManager;

import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import static xyz.n501yhappy.bubbleChat.utils.ChattingManager.chatMapping;

public final class BubbleChat extends JavaPlugin {
    public static BubbleChat instance;
    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new Chatting(),this);
        getServer().getPluginManager().registerEvents(new Moving(),this);

        ChattingManager.runClearInvidia();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        for (Map.Entry<UUID, Queue<MessageStruct>> queueEntry : chatMapping.entrySet()){
            Utils.clear(queueEntry.getKey());
        }
        //clear
    }
}
