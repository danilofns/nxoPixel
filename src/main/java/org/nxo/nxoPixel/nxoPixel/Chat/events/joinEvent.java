package org.nxo.nxoPixel.nxoPixel.Chat.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class joinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent player) {
        String playerName = player.getPlayer().getName();
        player.setJoinMessage("§f (+) §f"+ playerName);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent player) {
        String playerName = player.getPlayer().getName();
        player.setQuitMessage("§f (-) §f"+ playerName);
    }
}
