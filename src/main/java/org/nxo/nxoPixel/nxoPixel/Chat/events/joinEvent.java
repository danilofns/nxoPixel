package org.nxo.nxoPixel.nxoPixel.Chat.events;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.nxo.nxoPixel.nxoPixel.tags.playerFoolder;
import org.nxo.nxoPixel.nxoPixel.tags.tagsController;

public class joinEvent implements Listener {
    private final playerFoolder playerfoolder;
    public final JsonObject playerJson;
    private final tagsController tagscontroller;

    public joinEvent(playerFoolder playerfoolder, tagsController tagscontroller) {
        this.playerfoolder = playerfoolder;
        this.playerJson = playerfoolder.getPlayerJson();
        this.tagscontroller = tagscontroller;
    }

    public String showTag(Player player) {
        JsonElement getDisplay = playerJson.get(player.getName());
        JsonElement getTagFromGetDisplay = tagscontroller.tags.get(getDisplay.getAsString());
        return getTagFromGetDisplay.getAsString();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent player) {
        String getTag = showTag(player.getPlayer());
        player.setJoinMessage("§e (+) §f" + getTag + " " + player.getPlayer().getName());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent player) {
        String getTag = showTag(player.getPlayer());
        player.setQuitMessage("§c (-) §f" + getTag + " " + player.getPlayer().getName());
    }

    @EventHandler
    public void aoEntrar(PlayerJoinEvent playerJoin) {
        String getTag = showTag(playerJoin.getPlayer());
        playerJoin.getPlayer().sendMessage("§5§l[Nxo§c§lPixel] §f Bem-Vindo, §l" + getTag + " " + playerJoin.getPlayer().getName() + "§f, Ao servidor");
        soltarFogoDeArtificio(playerJoin.getPlayer());
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
