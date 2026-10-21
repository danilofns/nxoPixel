package org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.essentialsCommands.home.homeController;

public class renameHome implements CommandExecutor {

    private final homeController homecontroller;

    public renameHome(homeController homecontroller) {
        this.homecontroller = homecontroller;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage("§5§l[Nxo§c§lPixel] §fEste comando só é ativado por §lJogadores§f.");
            return true;
        }

        if(args.length != 2) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fUtilize: §3/renamehome <nome da home> <novo nome>");
            return true;
        }

        String nameHome = args[0];
        String newName = args[1];
        homecontroller.renameHome(player, nameHome, newName);

        return true;
    }
}
