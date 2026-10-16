package org.nxo.nxoPixel.nxoPixel.onJoin.events;

import org.bukkit.Color;
import org.bukkit.Effect;
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
        // 1. Invoca a entidade do Fogo de Artifício no local desejado
        Firework fw = (Firework) local.getWorld().spawnEntity(local, EntityType.FIREWORK_ROCKET);

        // 2. Pega os metadados para customizar a explosão
        FireworkMeta fwm = fw.getFireworkMeta();

        // 3. Cria o efeito visual (cores, tipo de explosão, etc.)
        FireworkEffect efeito = FireworkEffect.builder()
                .flicker(true)                                   // Efeito de cintilar/piscar
                .trail(true)                                     // Deixa rastro de fumaça
                .with(FireworkEffect.Type.BALL_LARGE)            // Tipo/Formato da explosão (BALL, STAR, BURST, CREEPER)
                .withColor(Color.RED, Color.GREEN, Color.BLUE)   // Cores principais do estouro
                .withFade(Color.YELLOW, Color.ORANGE)            // Cores para as quais ele desbota ao sumir
                .build();

        // 4. Aplica o efeito e define a altura do voo
        fwm.addEffect(efeito);
        fwm.setPower(1); // Altura que ele vai voar (1 equivale a cerca de 1 a 2 segundos de voo)

        // 5. Salva os metadados de volta na entidade
        fw.setFireworkMeta(fwm);
    }

}
