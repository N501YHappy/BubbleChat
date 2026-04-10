package xyz.n501yhappy.bubbleChat.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import xyz.n501yhappy.bubbleChat.MessageStruct;

import java.util.Locale;
import java.util.Queue;
import java.util.UUID;

import static xyz.n501yhappy.bubbleChat.ConfigLoader.*;
import static xyz.n501yhappy.bubbleChat.utils.ChattingManager.chatMapping;

public class Moving implements Listener {
    private static final double SNEAK_OFFSET = -0.3; //潜行时的偏移量
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        syncPos(event.getPlayer(),event.getPlayer().isSneaking());
    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event){
        syncPos(event.getPlayer(),event.isSneaking());
    }
    public static void syncPos(Player player, Boolean isSneaking){ //同步位置
        UUID uuid = player.getUniqueId();
        if (!chatMapping.containsKey(uuid) || chatMapping.get(uuid) == null || chatMapping.get(uuid).isEmpty()) return;

        //Calc done?
        Queue<MessageStruct> structs = chatMapping.get(uuid);
        int cnt = 0;
        for (MessageStruct struct : structs){
            if (!struct.hasArmorStand()) return;
            ArmorStand armorStand = (ArmorStand) Bukkit.getEntity(struct.getArmorID());
            if (armorStand == null) continue;
            armorStand.teleport(calcLocation(player.getLocation(),isSneaking).add(0,(EXPAND ? 1 : -1) * GAP*cnt ,0));
            cnt++;
        }
    }
    private static Location calcLocation(Location playerLoc, boolean isSneaking){

        //Calc 如果看不懂画个图应该就懂了
        double alpha = Math.toRadians(playerLoc.getYaw());
        double beta =  Math.toRadians(180 - playerLoc.getYaw() - 90); //恶心的Yaw 90度时x为负方向
        //Triangle_X
        double hypotenuse_X = OFFSET_X; //斜边
        double leg_bottom_X = Math.cos(beta) * hypotenuse_X; //邻边
        double leg_X = Math.sin(beta) * hypotenuse_X; //对边

        //Triangle_Z
        double hypotenuse_Z = OFFSET_Z; //斜边
        double leg_bottom_Z = Math.cos(alpha) * hypotenuse_Z; //邻边
        double leg_Z = Math.sin(alpha) * hypotenuse_Z; //对边

        double final_X = -(leg_bottom_X - leg_bottom_Z) +playerLoc.getX(); //因为在mc中，yaw为90度时X为负方向
        double final_Z = (leg_X + leg_Z) + playerLoc.getZ();


        Location location = new Location(playerLoc.getWorld(),final_X,playerLoc.getY() +  OFFSET_Y + (isSneaking ? SNEAK_OFFSET : 0),final_Z);
        return location;
    }
}
