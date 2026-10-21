package org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.homeController;

public class deleteHome implements CommandExecutor {

    private final homeController homecontroller;

    public deleteHome(homeController homecontroller) {
        this.homecontroller = homecontroller;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("§5§l[Nxo§c§lPixel] §fEsse comando só pode ser executado por player!.");
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fUse /deletehome <nome da home>");
            return true;
        }

        homecontroller.deleteHome(player, args[0]);

        player.sendMessage("§5§l[Nxo§c§lPixel] §fA home: §l"+ args[0] + "§f foi deletada com sucesso. \n");
        return true;
    }
}
