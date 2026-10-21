package org.nxo.nxoPixel.nxoPixel.Essentials.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class antiHunger implements Listener {
    @EventHandler
    public void antiHungerPlayer(FoodLevelChangeEvent food) {
        if(food.getEntity() instanceof Player player) {
            food.setCancelled(true);
            player.setFoodLevel(1000);
        }
    }
}
