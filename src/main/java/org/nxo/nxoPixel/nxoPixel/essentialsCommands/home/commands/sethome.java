package org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.homeController;

public class sethome implements CommandExecutor {

    private final homeController homecontroller;

    public sethome(homeController homecontroller) {
        this.homecontroller = homecontroller;
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

        if (args.length == 0) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fUse: /sethome <nome>");
            return true;
        }

        String nomeHome = args[0];

        Location localizacao = player.getLocation();
        homecontroller.setHome(player, nomeHome, localizacao);

        return true;
    }
}