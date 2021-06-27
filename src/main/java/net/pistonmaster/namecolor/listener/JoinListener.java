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

            if (plugin.getConfig().getBoolean("changePlayerListName"))
                player.setPlayerListName(plugin.getConfig().getString(player.getName()));
        }
    }
}
