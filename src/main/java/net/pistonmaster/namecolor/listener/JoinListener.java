package net.pistonmaster.namecolor.listener;

import lombok.RequiredArgsConstructor;
import net.pistonmaster.namecolor.PistonNC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class JoinListener implements Listener {
    private final PistonNC plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (plugin.getConfig().getString(e.getPlayer().getName()) != null) {
            player.setDisplayName(plugin.getConfig().getString(player.getName()));
            player.setPlayerListName(plugin.getConfig().getString(player.getName()));
        }
    }
}
