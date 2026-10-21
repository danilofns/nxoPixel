package org.nxo.nxoPixel.nxoPixel;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.nxo.nxoPixel.nxoPixel.Chat.events.Chat;
import org.nxo.nxoPixel.nxoPixel.Chat.events.joinEvent;
import org.nxo.nxoPixel.nxoPixel.Essentials.events.antiHunger;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.commands.*;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.homeController;
import org.nxo.nxoPixel.nxoPixel.onJoin.events.Eventos;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.homeManager;
import org.nxo.nxoPixel.nxoPixel.teleport.commands.teleportToPlayer;
import org.nxo.nxoPixel.nxoPixel.teleport.teleportController;

public final class NxoPixel extends JavaPlugin {

    @Override
    public void onLoad() {
        getLogger().info("Inicializando o plugin");
        Bukkit.getConsoleSender().sendMessage("Iniciando...");
    }

    @Override
    public void onEnable() {
        getLogger().info("NxoPixel: Iniciado com sucesso!.");

        homeManager homeManager = new homeManager(this);
        homeController homeController = new homeController(homeManager);

        teleportController controller = new teleportController();

        getCommand("sethome").setExecutor(new sethome(homeController));
        getCommand("home").setExecutor(new toHome(homeController));
        getCommand("homes").setExecutor(new listHomes(homeController));
        getCommand("deletehome").setExecutor(new deleteHome(homeController));
        getCommand("renamehome").setExecutor(new renameHome(homeController));
        getCommand("tpa").setExecutor(new teleportToPlayer(controller));

        Bukkit.getPluginManager().registerEvents(new joinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new Eventos(), this);
        Bukkit.getPluginManager().registerEvents(new Chat(), this);
        Bukkit.getPluginManager().registerEvents(new antiHunger(), this);

        Bukkit.getConsoleSender().sendMessage("$a NxoPixel: Ligado!.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
