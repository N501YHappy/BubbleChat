package xyz.n501yhappy.bubbleChat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.n501yhappy.bubbleChat.Utils;

public class Disappear implements Listener { //处理玩家突然消失
    @EventHandler
    public void onLeft(PlayerQuitEvent event){
        Utils.clear(event.getPlayer().getUniqueId());
    }
}
