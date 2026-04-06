package xyz.n501yhappy.bubbleChat.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import xyz.n501yhappy.bubbleChat.BubbleChat;
import xyz.n501yhappy.bubbleChat.MessageStruct;
import xyz.n501yhappy.bubbleChat.Utils;
import xyz.n501yhappy.bubbleChat.listeners.Moving;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChattingManager {
    public static Map<UUID, Queue<MessageStruct>> chatMapping = new ConcurrentHashMap<>();
    public static void chat(Player player,String message){
        UUID uuid = player.getUniqueId();
        MessageStruct struct = new MessageStruct(message,System.currentTimeMillis());
        Queue<MessageStruct> queue = chatMapping.computeIfAbsent(uuid,k -> new ConcurrentLinkedQueue<>());
        queue.offer(struct);
        Bukkit.getScheduler().runTask(BubbleChat.instance,() -> {
            Utils.spawnFloatingText(message,player.getLocation(),struct);
            Moving.syncPos(player,player.isSneaking());
        });
    }
    public static void runClearInvidia(){ //清理无效的
        Bukkit.getScheduler().runTaskTimer(BubbleChat.instance, () -> {
            for(Map.Entry<UUID,Queue<MessageStruct>> structs : chatMapping.entrySet()){
                Queue<MessageStruct> queue = structs.getValue();
                if (queue.isEmpty()) return;
                while (!queue.isEmpty() && queue.peek().isExpired()){
                    ArmorStand armorStand = (ArmorStand) Bukkit.getEntity(queue.peek().getArmorID());
                    if (armorStand != null) armorStand.remove();
                    queue.poll();
                    Moving.syncPos(Bukkit.getPlayer(structs.getKey()),Bukkit.getPlayer(structs.getKey()).isSneaking()); //更新一下列表
                }
            }
        }, 0L, 1L); //50ms / once
    }
}
