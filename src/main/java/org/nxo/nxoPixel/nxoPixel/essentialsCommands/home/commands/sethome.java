package org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.HomeManager;

public class sethome implements CommandExecutor {

    private final HomeManager homeManager;

    public sethome(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Apenas jogadores podem executar este comando!");
            return true;
        }

        // Verifica se o jogador informou o nome da home
        if (args.length == 0) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fUse: /sethome <nome>");
            return true;
        }

        String nomeHome = args[0];

        Location localizacao = player.getLocation();
        homeManager.setHome(player, nomeHome, localizacao);

        return true;
    }
}