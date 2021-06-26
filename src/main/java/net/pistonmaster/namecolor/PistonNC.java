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

        log.info(ChatColor.AQUA + "Registering events");
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(this), this);

        log.info(ChatColor.AQUA + "Registering command");
        Bukkit.getServer().getPluginCommand("nc").setExecutor(new NCCommand(this));
        Bukkit.getServer().getPluginCommand("nc").setTabCompleter(new NCCommand(this));

        Bukkit.getServer().getPluginCommand("ic").setExecutor(new ICComand());
        Bukkit.getServer().getPluginCommand("ic").setTabCompleter(new ICComand());

        log.info(ChatColor.AQUA + "Done! :D");
    }
}
