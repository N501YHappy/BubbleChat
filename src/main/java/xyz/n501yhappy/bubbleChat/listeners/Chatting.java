package xyz.n501yhappy.bubbleChat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.n501yhappy.bubbleChat.utils.ChattingManager;

public class Chatting implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String message = event.getMessage();
        ChattingManager.chat(event.getPlayer(), message);
    }
}
