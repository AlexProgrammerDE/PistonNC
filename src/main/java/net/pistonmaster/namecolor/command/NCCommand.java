package net.pistonmaster.namecolor.command;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.pistonmaster.namecolor.PistonNC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class NCCommand implements CommandExecutor, TabExecutor {
    private final PistonNC plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length != 0) {
                if (player.hasPermission("piston.nc")) {
                    if ("reset".equals(args[0])) {
                        player.setDisplayName(ChatColor.RESET + player.getName() + ChatColor.RESET);
                        player.setPlayerListName(ChatColor.RESET + player.getName() + ChatColor.RESET);
                        plugin.getConfig().set(player.getName(), ChatColor.RESET + player.getName() + ChatColor.RESET);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Your name has been reset");
                        return true;
                    } else if (ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', args[0])).equals(player.getName())) {
                        player.setDisplayName(translate(args[0]));
                        player.setPlayerListName(translate(args[0]));
                        plugin.getConfig().set(player.getName(), translate(args[0]));
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Your name has been set to: " + translate(args[0]));
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "PistonNC " + ChatColor.DARK_AQUA + "Incorrect colour specification Type '/nc' for help");
                    }

                    return true;
                } else {
                    player.sendMessage(ChatColor.YELLOW + "PistonNC " + ChatColor.DARK_AQUA + "You must donate to the server to use this command. Type /donate to donate.");
                }

            } else {
                player.spigot().sendMessage(new TextComponent(ChatColor.BLUE + "--------------------------------------------"));
                player.spigot().sendMessage(new TextComponent(ChatColor.AQUA + "(( PistonNC ))"));
                player.spigot().sendMessage(new TextComponent(ChatColor.YELLOW + "(( /nc <name with color>> ))"));
                player.spigot().sendMessage(new TextComponent(ChatColor.YELLOW + "(( /nc reset to reset your name ))"));

                player.sendMessage("");

                player.sendMessage(ChatColor.AQUA + "Valid Colors:");
                player.sendMessage("§0&0 §1&1 §2&3 §3&3 §4&4 §5&5 §6&6 §7&7 §8&8 §9&9");
                player.sendMessage("§a&a §b&b §c&c §d&d §e&e §f&f");

                player.sendMessage("");

                player.sendMessage(ChatColor.AQUA + "Valid format:");
                player.sendMessage("§k&k §l&l §m&m §n&n §o&o §r&r");
                player.spigot().sendMessage(new TextComponent(ChatColor.BLUE + "--------------------------------------------"));
            }

        } else {
            sender.sendMessage(ChatColor.YELLOW + "PistonNC " + ChatColor.DARK_AQUA + "You must join the server to use this command");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }

    private String translate(String str) {
        return ChatColor.translateAlternateColorCodes('&', str).replace("§k", "");
    }
}
