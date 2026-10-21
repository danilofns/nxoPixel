package org.nxo.nxoPixel.nxoPixel.essentialsCommands.home;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class homeController {

    private final homeManager homeManager;
    private final JsonObject homes;

    public homeController(homeManager homeManager) {
        this.homeManager = homeManager;
        this.homes = homeManager.getHomes();
    }

    public String uuid(Player player) {
        return player.getUniqueId().toString();
    }

    public Boolean hasHome(Player player) {
        String uuid = uuid(player);
        String args = "§5§l[Nxo§c§lPixel] §f Você não possui locais marcados, utilize: /sethome <nome> \n";
        JsonObject homesPlayer = homes.getAsJsonObject(uuid);
        if(!homes.has(uuid)) {
            player.sendMessage(args);
            return true;
        } else if(homesPlayer.isEmpty()) {
            player.sendMessage(args);
            return true;
        }
        return false;
    }

    public void setHome(Player player, String nomeHome, Location location) {
        String uuid = uuid(player);

        if (!homes.has(uuid)) {
            homes.add(uuid, new JsonObject());
        }

        JsonObject homesPlayer = homes.getAsJsonObject(uuid);
        JsonObject dadosHome = new JsonObject();

        dadosHome.addProperty("world", Objects.requireNonNull(location.getWorld()).getName());
        dadosHome.addProperty("x", location.getX());
        dadosHome.addProperty("y", location.getY());
        dadosHome.addProperty("z", location.getZ());
        dadosHome.addProperty("pitch", location.getPitch());
        dadosHome.addProperty("yaw", location.getYaw());

        homesPlayer.add(nomeHome, dadosHome);
        player.sendMessage("§5§l[Nxo§c§lPixel] §f Localização salva com sucesso!. \n");

        homeManager.salvarArquivo();
    }

    public void home(Player player, String nomeHome) {
        String uuid = uuid(player);
        int i;
        if(!hasHome(player)) {
            JsonObject homesPlayer = homes.getAsJsonObject(uuid);
            List<String> keys = new ArrayList<>(homesPlayer.keySet());
            for(i = 0; i < homesPlayer.size(); i++) {
                String keyName = keys.get(i);
                if(Objects.equals(keyName, nomeHome)) {
                    JsonObject homesObject = homesPlayer.getAsJsonObject(nomeHome);
                    World world = Bukkit.getWorld(homesObject.get("world").getAsString());
                    double x = homesObject.get("x").getAsDouble();
                    double y = homesObject.get("y").getAsDouble();
                    double z = homesObject.get("z").getAsDouble();
                    float pitch = homesObject.get("pitch").getAsFloat();
                    float yaw = homesObject.get("yaw").getAsFloat();
                    Location toHome = new Location(world,x,y,z,yaw,pitch);
                    player.teleport(toHome);
                    return;
                }
            }
        }
        player.sendMessage("§5§l[Nxo§c§lPixel] §fVocê não possui essa home");
    }

    public void homes(Player player) {
        String uuid = uuid(player);
        int i;

        if(!hasHome(player)) {
            JsonObject homesPlayer = homes.getAsJsonObject(uuid);
            List<String> keys = new ArrayList<>((homesPlayer.keySet()));
            List<String> allHomes = new ArrayList<>();
            for(i = 0; i < homesPlayer.size(); i++){
                String homeName = keys.get(i);
                allHomes.add(homeName);
            }
            player.sendMessage("§5§l[Nxo§c§lPixel] §f Essas são todas as homes que você possui: \n");
            for(i = 0; i < allHomes.size(); i++) {
                player.sendMessage(" §f" + (i+1) + "º §3§l" + allHomes.get(i));
            }
        }
    }

    public void deleteHome(Player player, String home) {
        String uuid = uuid(player);
        int i;

        if(!hasHome(player)) {
            JsonObject homesPlayer = homes.getAsJsonObject(uuid);
            List<String> keys = new ArrayList<>((homesPlayer.keySet()));
            for(i = 0; i < homesPlayer.size(); i++) {
                String homeName = keys.get(i);
                if(Objects.equals(homeName, home)) {
                    homesPlayer.remove(home);
                }
            }
            homeManager.salvarArquivo();
        }
    }

    public void renameHome(Player player, String oldName, String newName) {
        int i;
        String uuid = uuid(player);

        if(!hasHome(player)) {
            JsonObject homesPlayer = homes.getAsJsonObject(uuid);
            List<String> keys = new ArrayList<>((homesPlayer.keySet()));
            for(i = 0; i < homesPlayer.size(); i++) {
                String homeName = keys.get(i);
                if(Objects.equals(oldName, homeName)) {
                    JsonElement newHome = homesPlayer.remove(oldName);
                    if(newHome != null) {
                        homesPlayer.add(newName, newHome);
                        player.sendMessage("§5§l[Nxo§c§lPixel] §fNome da home alterado com sucesso!.");
                        homeManager.salvarArquivo();
                    }
                }
            }
        }
    }
}
