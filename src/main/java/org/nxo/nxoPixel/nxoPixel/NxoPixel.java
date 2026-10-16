package org.nxo.nxoPixel.nxoPixel;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.nxo.nxoPixel.nxoPixel.Chat.events.Chat;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.commands.*;
import org.nxo.nxoPixel.nxoPixel.onJoin.events.Eventos;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.HomeManager;

public final class NxoPixel extends JavaPlugin {


    @Override
    public void onLoad() {
        getLogger().info("Inicializando o plugin");
        Bukkit.getConsoleSender().sendMessage("Iniciando...");
    }
    @Override
    public void onEnable() {
        getLogger().info("NxoPixel: Iniciado com sucesso!.");
        HomeManager homeManager = new HomeManager(this);

        getCommand("sethome").setExecutor(new sethome(homeManager));
        getCommand("home").setExecutor(new toHome(homeManager));
        getCommand("homes").setExecutor(new listHomes(homeManager));
        getCommand("deletehome").setExecutor(new deleteHome(homeManager));
        getCommand("renamehome").setExecutor(new renameHome(homeManager));

        Bukkit.getPluginManager().registerEvents(new Eventos(), this);
        Bukkit.getPluginManager().registerEvents(new Chat(), this);
        Bukkit.getConsoleSender().sendMessage("$a NxoPixel: Ligado!.");
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
