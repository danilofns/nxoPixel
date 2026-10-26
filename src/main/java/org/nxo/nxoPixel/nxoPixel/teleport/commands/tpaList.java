package org.nxo.nxoPixel.nxoPixel.teleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.teleport.teleportController;

public class tpaList implements CommandExecutor {

    private final teleportController teleportController;

    public tpaList(teleportController teleportController) {
        this.teleportController = teleportController;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("");
            return true;
        }

        teleportController.listTpa(player);
        return true;
    }
}
