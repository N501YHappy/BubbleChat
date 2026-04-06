package xyz.n501yhappy.bubbleChat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.n501yhappy.bubbleChat.ConfigLoader;

import static xyz.n501yhappy.bubbleChat.ConfigLoader.PREFIX;

public class BubbleChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage( PREFIX+ ChatColor.RED + "You have no permissions to do that");
            return true;
        }
        sender.sendMessage(ConfigLoader.PREFIX + ChatColor.YELLOW + "Configuration reloading...");

        ConfigLoader.reload();

        sender.sendMessage(ConfigLoader.PREFIX + ChatColor.GREEN + "Configuration reloaded successfully!");
        return true;
    }
}
