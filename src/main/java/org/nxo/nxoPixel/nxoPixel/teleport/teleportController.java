package org.nxo.nxoPixel.nxoPixel.teleport;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class teleportController {

    private final JsonObject teleport;

    public teleportController(JsonObject teleport) {
        this.teleport = teleport;
    }

    public void listTpa(Player player) {
        String uuid = player.getUniqueId().toString();
        JsonObject getTpaList = teleport.getAsJsonObject(uuid);
        if(getTpaList == null) {
            player.sendMessage("Você não possui pedidos de teletransporte.");
            return;
        }
        JsonArray getTpaArray = getTpaList.getAsJsonArray("teleport-request");
        if(getTpaArray.size() <= 0) {
            player.sendMessage("Você não possui pedidos de teletransporte.");
            return;
        }
        player.sendMessage("Lista de teletransporte:");
        for(int i=0; i <getTpaArray.size(); i++) {
            player.sendMessage(i+1+ "º -> " + getTpaArray.get(i).getAsString());
        }
    }
    public boolean tpExist(Player playerRequestTp, String namePlayerToRequest) {
        Player playerToTp = Bukkit.getPlayerExact(namePlayerToRequest);
        if (playerToTp != null && playerRequestTp != null) {
            String uuid = playerToTp.getUniqueId().toString();
            JsonObject objectTp = teleport.getAsJsonObject(uuid);
            if (objectTp == null) {
                listToTp(playerRequestTp, namePlayerToRequest);
                return false;
            }
            JsonArray teleportRequest = objectTp.getAsJsonArray("teleport-request");
            for (int i = 0; i < teleportRequest.size(); i++) {
                String namePlayer = teleportRequest.get(i).getAsString();
                if (Objects.equals(playerRequestTp.getName(), namePlayer)) {
                    return true;
                }
            }
            listToTp(playerRequestTp, namePlayerToRequest);
        }
        return false;
    }

    public void removeTeleportFromList(Player listPlayer, String playerToRemove) {
        String uuid = listPlayer.getUniqueId().toString();
        JsonObject getObjectTeleport = teleport.getAsJsonObject(uuid);
        if (!getObjectTeleport.isEmpty()) {
            JsonArray getArray = getObjectTeleport.getAsJsonArray("teleport-request");
            for (int i = 0; i < getArray.size(); i++) {
                String name = getArray.get(i).getAsString();
                if (Objects.equals(name, playerToRemove)) {
                    getArray.remove(i);
                }
            }
        }
    }

    public boolean logicTpAccept(Player playerTpAccept, String playerNameRequest) {
        String uuid = playerTpAccept.getUniqueId().toString();
        Player playerRequest = Bukkit.getPlayerExact(playerNameRequest);
        if (uuid != null && playerNameRequest != null) {
            JsonObject controller = teleport.getAsJsonObject(uuid);
            JsonArray getArray = controller.get("teleport-request").getAsJsonArray();
            for (int i = 0; i < getArray.size(); i++) {
                String namePlayer = getArray.get(i).getAsString();
                if (Objects.equals(namePlayer, playerNameRequest)) {
                    Location targetLocation = playerTpAccept.getLocation();
                    if (playerRequest != null) {
                        playerRequest.teleport(targetLocation);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void listToTp(Player playerRequestTp, String namePlayerToRequest) {
        Player playerToTp = Bukkit.getPlayerExact(namePlayerToRequest);
        String playerRequestTpName = playerRequestTp.getName();
        if (playerToTp != null) {
            String uuid = playerToTp.getUniqueId().toString();
            if (!teleport.has(uuid)) {
                teleport.add(uuid, new JsonObject());
            }
            JsonObject controller = teleport.getAsJsonObject(uuid);

            if (controller.has("teleport-request")) {
                JsonArray arrayTp = controller.get("teleport-request").getAsJsonArray();
                arrayTp.add(playerRequestTpName);
                controller.remove("teleport-request");
                controller.add("teleport-request", arrayTp);
            } else {
                JsonArray playerInArray = new JsonArray();
                playerInArray.add(playerRequestTpName);
                controller.add("teleport-request", playerInArray);
            }
            playerToTp.sendMessage("§5§l[Nxo§c§lPixel] §fVocê recebeu um pedido de teleport" + playerRequestTp.getName());
        }
    }

    public void playerTeleportAccept(Player playerTpAccept, String playerNameRequest) {
        Player playerForAccept = Bukkit.getPlayerExact(playerNameRequest);

        if (playerForAccept == null) {
            playerTpAccept.sendMessage("§5§l[Nxo§c§lPixel] §fEste jogador não está online.");
            return;
        }

        if (playerForAccept.equals(playerTpAccept)) {
            playerTpAccept.sendMessage("§5§l[Nxo§c§lPixel] §fVocê não aceitar seu própio TPA.");
            return;
        }

        if (logicTpAccept(playerTpAccept, playerNameRequest)) {
            removeTeleportFromList(playerTpAccept, playerNameRequest);
        } else {
            playerTpAccept.sendMessage("§5§l[Nxo§c§lPixel] §fVocê não possui um pedido de teletransporte.");
        }
    }

    public void playerTeleport(Player player, String playerName) {
        Player playerForTeleport = Bukkit.getPlayerExact(playerName);

        if (playerForTeleport == null) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fEste jogador não está online.");
            return;
        }

        if (playerForTeleport.equals(player)) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fVocê já está na sua própria localização!");
            return;
        }
        if(!tpExist(player, playerName)) {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fPedido de teleport enviado para " + playerName);
        } else {
            player.sendMessage("§5§l[Nxo§c§lPixel] §fVocê já fez um pedido de teletransporte ao "+ playerName);
        }
    }
}
