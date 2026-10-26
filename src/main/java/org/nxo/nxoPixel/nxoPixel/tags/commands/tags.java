package org.nxo.nxoPixel.nxoPixel.tags.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.tags.tagsController;

public class tags implements CommandExecutor {

    private final tagsController tagscontroller;

    public tags(tagsController tagscontroller) {
        this.tagscontroller = tagscontroller;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("Apenas jogadores podem usar este comando.");
            return true;
        }
        tagscontroller.tagsList(player);
        return true;
    }
}
