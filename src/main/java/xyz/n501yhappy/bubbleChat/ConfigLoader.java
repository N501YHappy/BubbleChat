package xyz.n501yhappy.bubbleChat;

import org.bukkit.configuration.Configuration;

import java.util.Objects;

import static xyz.n501yhappy.bubbleChat.BubbleChat.instance;

public class ConfigLoader {
    public static String PREFIX = "&7Bubble&aChat &6>> ";
    public static Double OFFSET_X = 0.0;
    public static Double OFFSET_Y = 1.4;
    public static Double OFFSET_Z = 0.7;
    public static Boolean EXPAND = true; //true for up false for down
    public static Double GAP = 0.1;
    public static void reload(){
        instance.reloadConfig();
        instance.saveDefaultConfig();
        Configuration config = instance.getConfig();
        PREFIX = config.getString("prefix",PREFIX);PREFIX = toColorful(PREFIX);
        OFFSET_X = config.getDouble("offsets.offset_x",OFFSET_X);
        OFFSET_Y = config.getDouble("offsets.offset_y",OFFSET_Y);
        OFFSET_Z = config.getDouble("offsets.offset_z",OFFSET_Z);
        EXPAND = Objects.equals(config.getString("expand"), "up");
        GAP = config.getDouble("gap",GAP);
    }
    private static String toColorful(String string){
    return string.replace('&','§');
    }
}
