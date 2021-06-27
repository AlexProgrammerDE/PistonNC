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
package net.pistonmaster.namecolor;

import net.pistonmaster.namecolor.command.ICComand;
import net.pistonmaster.namecolor.command.NCCommand;
import net.pistonmaster.namecolor.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PistonNC extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Logger log = getLogger();

        log.info(ChatColor.AQUA + "Loading config");
        saveDefaultConfig();

        log.info(ChatColor.AQUA + "Registering listeners");
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(this), this);

        log.info(ChatColor.AQUA + "Registering commands");
        Bukkit.getServer().getPluginCommand("nc").setExecutor(new NCCommand(this));
        Bukkit.getServer().getPluginCommand("nc").setTabCompleter(new NCCommand(this));

        Bukkit.getServer().getPluginCommand("ic").setExecutor(new ICComand());
        Bukkit.getServer().getPluginCommand("ic").setTabCompleter(new ICComand());

        log.info(ChatColor.AQUA + "Done! :D");
    }
}
