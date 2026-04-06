package xyz.n501yhappy.bubbleChat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import static xyz.n501yhappy.bubbleChat.utils.ChattingManager.chatMapping;

public class Utils {
    public static Double OFFSET_TEXT = 1.5;
    public static ArmorStand spawnFloatingText(String message, Location location,MessageStruct struct) {
        World world = location.getWorld();
        if (world == null) return null;
        location.setY(location.getY() + OFFSET_TEXT);
        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);


        armorStand.setArms(false);
        armorStand.setBasePlate(false);
        armorStand.setMarker(true);
        armorStand.setSmall(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);

        armorStand.setCustomName(message);
        armorStand.setCustomNameVisible(true);
        struct.setArmorID(armorStand.getUniqueId());
        return armorStand;
    }
    public static void clear(UUID uuid){
        if (chatMapping.get(uuid) == null || chatMapping.get(uuid).isEmpty()) return;
        Queue<MessageStruct> structs = chatMapping.get(uuid);
        while (!structs.isEmpty()){
            MessageStruct struct = structs.poll();
            ArmorStand armorStand = (ArmorStand) Bukkit.getEntity(struct.getArmorID());
            if (armorStand == null) continue;
            armorStand.remove();
        }
    }
}
