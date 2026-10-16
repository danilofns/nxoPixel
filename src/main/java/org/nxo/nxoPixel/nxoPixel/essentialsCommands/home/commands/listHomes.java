package org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.HomeManager;

public class listHomes implements CommandExecutor {

    private final HomeManager homeManager;

    public listHomes(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Apenas jogadores podem executar este comando!");
            return true;
        }

        if (args.length != 0) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fUse apenas /homes para lista-las");
            return true;
        }

        homeManager.homes(player);

        return true;
    }
}
