/*
 * #%L
 * PistonNC
 * %%
 * Copyright (C) 2021 AlexProgrammerDE
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
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
                        player.setDisplayName(null);
                        player.setPlayerListName(null);
                        plugin.getConfig().set(player.getName(), null);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Your name has been reset");
                        return true;
                    } else if (ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', args[0])).equals(player.getName())) {
                        player.setDisplayName(translate(args[0]));

                        if (plugin.getConfig().getBoolean("changePlayerListName"))
                            player.setPlayerListName(translate(args[0]));

                        plugin.getConfig().set(player.getName(), translate(args[0]));
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Your name has been set to: " + ChatColor.RESET + translate(args[0]));
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
                player.sendMessage("§l&l§r §m&m§r §n&n§r §o&o§r §r&r");

                player.sendMessage("");
                player.sendMessage(ChatColor.AQUA + "Examples:");
                player.sendMessage("&c" + player.getName() + " = §c" + player.getName());
                player.sendMessage("&a&l" + player.getName() + " = §a§l" + player.getName());

                if (player.getName().length() > 1) {
                    final int mid = player.getName().length() / 2; //get the middle of the String
                    String[] parts = {player.getName().substring(0, mid), player.getName().substring(mid)};

                    player.sendMessage("&a" + parts[0] + "&c&l" + parts[1] + " = §a" + parts[0] + "§c§l" + parts[1]);
                }

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
