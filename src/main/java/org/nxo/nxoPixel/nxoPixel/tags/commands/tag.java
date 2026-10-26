package org.nxo.nxoPixel.nxoPixel.tags.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nxo.nxoPixel.nxoPixel.tags.tagsController;

public class tag implements CommandExecutor {

    private final tagsController tagscontroller;

    public tag(tagsController tagscontroller) {
        this.tagscontroller = tagscontroller;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("Apenas jogadores podem usar essa tag.");
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §futilize /tag <tag>");
            return true;
        }

        String tag = args[0];
        if(tag.equals("criar")) {
            if(args.length == 3) {
                String tagName = args[1];
                String prefixTagAfter = args[2];
                String prefixTag = prefixTagAfter + " §f";
                tagscontroller.createTag(tagName,prefixTag);
                return true;
            }
            player.sendMessage("§5§l[Nxo§c§lPixel] §fUtilize /tag criar <nome da tag> <prefixo>");
            return true;
        }

        if(tag.equals("remover")) {
            if(args.length == 2) {
                String removerTag = args[1];
                tagscontroller.removeTag(player, removerTag);
                return true;
            }
            player.sendMessage("§5§l[Nxo§c§lPixel] §fUtilize /tag remover <nome da tag>");
            return true;
        }

        if(tag.equals("list")) {
            tagscontroller.tagsList(player);
            return true;
        }

        tagscontroller.tagVerify(player,tag);
        return true;
    }
}
