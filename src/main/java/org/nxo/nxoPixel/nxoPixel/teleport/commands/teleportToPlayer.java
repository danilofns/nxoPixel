package org.nxo.nxoPixel.nxoPixel.teleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.teleport.teleportController;

public class teleportToPlayer implements CommandExecutor {

    private final teleportController teleportController;

    public teleportToPlayer(teleportController teleportController) {
        this.teleportController = teleportController;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage();
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("utilize: /tpa <player>");
            return true;
        }

        String playerName = args[0];
        teleportController.playerTeleport(player, playerName);
        return true;
    }
}
