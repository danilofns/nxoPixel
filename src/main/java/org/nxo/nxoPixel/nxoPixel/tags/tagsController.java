package org.nxo.nxoPixel.nxoPixel.tags;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class tagsController implements Listener {

    private final tagsFoolder tagsfoolder;
    private final playerFoolder playerfoolder;

    public JsonObject tags;
    public JsonObject playersJson;

    public tagsController(tagsFoolder tagsfoolder, playerFoolder playerfoolder) {
        this.tagsfoolder = tagsfoolder;
        this.playerfoolder = playerfoolder;
        this.tags = tagsfoolder.getTags();
        this.playersJson = playerfoolder.getPlayerJson();
    }

    public void removeTag(Player player, String tagRemove) {
        if(tags.has(tagRemove)) {
            tags.remove(tagRemove);
            tagsfoolder.saveTags();
            player.sendMessage("§5§l[Nxo§c§lPixel] §fTag removida");
            return;
        }
        player.sendMessage("§5§l[Nxo§c§lPixel] §fEssa tag não existe.");
    }

    public void tagsList(Player player) {
       if(!tags.isEmpty()) {
           player.sendMessage("§5§l[Nxo§c§lPixel] §f Lista de tags:");
           for(String key : tags.keySet()) {
               JsonElement valorTag = tags.get(key);
               String valor = valorTag.getAsString();
               player.sendMessage("§fTag: "+ key + " Prefixo: "+ valor);
           }
       }
    }

    public void tagVerify(Player player, String tag) {
        if(tags.has(tag)) {
            playersJson.remove(player.getName());
            playersJson.addProperty(player.getName(), tag);
            playerfoolder.saveTags();
            changeTag(player);
            return;
        }
        player.sendMessage("§5§l[Nxo§c§lPixel] §fNão existe nenhuma tag!.");
    }

    public void createTag(String tag, String prefixTag) {
        if(!tags.has(tag)) {
            String formatedPrefixTag = prefixTag.replace("&", "§");
            tags.addProperty(tag, formatedPrefixTag);
            tagsfoolder.saveTags();
        }
    }

    public void changePlayerNameTag(Player player, String display) {
        player.setDisplayName(display);
        player.setPlayerListName(display + player.getName());
        player.setPlayerListHeader(display + player.getName());

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        String teamName = player.getName().toLowerCase();
        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }
        team.addEntry(player.getName());
        team.setPrefix(display);
    }

    public void changePlayerChatTag(Player player, String display) {
        if(playersJson.get(player.getName()) != null) {
            player.setDisplayName(display);
            changePlayerNameTag(player,display );
        }
    }

    @EventHandler
    public void tags(PlayerJoinEvent player) {
        Player playerP = player.getPlayer();
        if(!playerP.hasPlayedBefore() || playersJson.get(playerP.getName()) == null) {
            String membro = "membro";
            if(!tags.has(membro)) {
                String display = "§7[Membro]§f ";
                createTag(membro, display);
                tagsfoolder.saveTags();
            }
            playersJson.addProperty(playerP.getName(), membro);
            changeTag(playerP);
            playerfoolder.saveTags();
        } else {
            if(playersJson.get(playerP.getName()) != null) {
                changeTag(playerP);
            }
        }
    }

    public void changeTag(Player player) {
        JsonElement getDisplay = playersJson.get(player.getName());
        String display = tags.get(getDisplay.getAsString()).getAsString();
        changePlayerChatTag(player, display);
        changePlayerNameTag(player, display);
    }
}
