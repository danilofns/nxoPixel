package org.nxo.nxoPixel.nxoPixel;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.nxo.nxoPixel.nxoPixel.Chat.events.Chat;
import org.nxo.nxoPixel.nxoPixel.Chat.events.joinEvent;
import org.nxo.nxoPixel.nxoPixel.Essentials.events.antiHunger;
import org.nxo.nxoPixel.nxoPixel.Essentials.events.deathPlayer;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.commands.*;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.homeController;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.homeManager;
import org.nxo.nxoPixel.nxoPixel.tags.commands.tag;
import org.nxo.nxoPixel.nxoPixel.tags.commands.tags;
import org.nxo.nxoPixel.nxoPixel.tags.playerFoolder;
import org.nxo.nxoPixel.nxoPixel.tags.tagsController;
import org.nxo.nxoPixel.nxoPixel.tags.tagsFoolder;
import org.nxo.nxoPixel.nxoPixel.teleport.commands.teleportAccept;
import org.nxo.nxoPixel.nxoPixel.teleport.commands.teleportToPlayer;
import org.nxo.nxoPixel.nxoPixel.teleport.commands.tpaList;
import org.nxo.nxoPixel.nxoPixel.teleport.teleportController;

public final class NxoPixel extends JavaPlugin {

    JsonObject teleport = new JsonObject();
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
        teleportController controller = new teleportController(teleport);
        playerFoolder playerFoolder = new playerFoolder(this);
        tagsFoolder tagsFoolder = new tagsFoolder(this);
        tagsController controllerTag = new tagsController(tagsFoolder, playerFoolder);

        getCommand("sethome").setExecutor(new sethome(homeController));
        getCommand("home").setExecutor(new toHome(homeController));
        getCommand("homes").setExecutor(new listHomes(homeController));
        getCommand("deletehome").setExecutor(new deleteHome(homeController));
        getCommand("renamehome").setExecutor(new renameHome(homeController));

        getCommand("tpa").setExecutor(new teleportToPlayer(controller));
        getCommand("tpaccept").setExecutor(new teleportAccept(controller));
        getCommand("tpalist").setExecutor(new tpaList(controller));

        getCommand("tag").setExecutor(new tag(controllerTag));
        getCommand("tags").setExecutor(new tags(controllerTag));

        Bukkit.getPluginManager().registerEvents(new joinEvent(playerFoolder, controllerTag), this);
        Bukkit.getPluginManager().registerEvents(new Chat(), this);
        Bukkit.getPluginManager().registerEvents(new antiHunger(), this);
        Bukkit.getPluginManager().registerEvents(new deathPlayer(), this);
        Bukkit.getPluginManager().registerEvents(new tagsController(tagsFoolder, playerFoolder), this);

        Bukkit.getConsoleSender().sendMessage("$a NxoPixel: Ligado!.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
