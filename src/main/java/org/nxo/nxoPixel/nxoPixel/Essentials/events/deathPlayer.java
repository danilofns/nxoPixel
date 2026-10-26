package org.nxo.nxoPixel.nxoPixel.Essentials.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class deathPlayer implements Listener {

    @EventHandler
    public void onDeathByPlayer(PlayerDeathEvent playerDeath) {
        Player playerName = playerDeath.getEntity();
        Player murder = playerName.getKiller();
        if(murder != null) {
            String item = String.valueOf(murder.getItemInUse());
            playerKilAnother(String.valueOf(playerName),playerDeath,murder, item);
            return;
        }
        playerDeath(playerDeath);
    }

    @EventHandler
    public void keepSomethings(PlayerDeathEvent player) {
        player.setKeepInventory(true);
        player.setKeepLevel(true);
    }

    public void playerKilAnother(String playerName, PlayerDeathEvent player, Player murder, String item) {
        player.setDeathMessage(playerName + " foi morto por "+ murder + " utilizando " + item);
    }

    public void playerDeath(PlayerDeathEvent playerDeath) {
        playerDeath.setDeathMessage("");
    }
}
