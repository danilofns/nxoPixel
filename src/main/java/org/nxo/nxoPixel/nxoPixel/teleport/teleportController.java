package org.nxo.nxoPixel.nxoPixel.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class teleportController {

    public void playerTeleport(Player player, String playerName) {
        Player playerForTeleport = Bukkit.getPlayerExact(playerName);

        if (playerForTeleport == null) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fEste jogador não está online.");
            return;
        }

        if (playerForTeleport.equals(player)) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fVocê já está na sua própria localização!");
            return;
        }

        Location targetLocation = playerForTeleport.getLocation();

        player.teleport(targetLocation);

        player.sendMessage("§5§l[Nxo§c§lPixel] §fVocê foi teleportado até " + playerForTeleport.getName() + ".");
    }
}
