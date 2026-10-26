package org.nxo.nxoPixel.nxoPixel.teleport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.teleport.teleportController;

public class teleportAccept implements CommandExecutor {

    private final teleportController teleportcontroller;

    public teleportAccept(teleportController teleportcontroller) {
        this.teleportcontroller = teleportcontroller;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("§5§l[Nxo§c§lPixel] §fEste comando só pode ser executado por jogadores");
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fUtilize /tpaccept <nome do jogador>");
            return true;
        }

        String namePlayer = args[0];

        teleportcontroller.playerTeleportAccept(player, namePlayer);
        return true;
    }
}
