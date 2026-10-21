package org.nxo.nxoPixel.nxoPixel.onJoin.events;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class Eventos implements Listener {

    @EventHandler
    public void aoEntrar(PlayerJoinEvent playerJoin) {
        Player player = playerJoin.getPlayer();
        player.sendMessage("§5§l[Nxo§c§lPixel] §f Bem-Vindo, §l" + player.getName() + "§f, Ao servidor");
        soltarFogoDeArtificio(player);
    }

    public void soltarFogoDeArtificio(Player player) {
        Location local = player.getLocation();
        Firework fw = (Firework) local.getWorld().spawnEntity(local, EntityType.FIREWORK_ROCKET);
        FireworkMeta fwm = fw.getFireworkMeta();
        FireworkEffect efeito = FireworkEffect.builder()
                .flicker(true)
                .trail(true)
                .with(FireworkEffect.Type.BALL_LARGE)
                .withColor(Color.RED, Color.GREEN, Color.BLUE)
                .withFade(Color.YELLOW, Color.ORANGE)
                .build();

        fwm.addEffect(efeito);
        fwm.setPower(1);

        fw.setFireworkMeta(fwm);
    }

}
