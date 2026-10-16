package org.nxo.nxoPixel.nxoPixel.essentialsCommands.home;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeManager {

    private final File arquivo;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private JsonObject homes;

    public HomeManager(JavaPlugin plugin) {
        this.arquivo = new File(plugin.getDataFolder(), "homes.json");

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        carregarHomes();
    }

    private void carregarHomes() {
        if (arquivo.exists()) {
            try (FileReader reader = new FileReader(arquivo)) {
                homes = gson.fromJson(reader, JsonObject.class);

                if (homes == null) {
                    homes = new JsonObject();
                }
            } catch (IOException e) {
                throw new RuntimeException(
                        "Erro ao carregar homes.json", e
                );
            }
        } else {
            homes = new JsonObject();
            salvarArquivo();
        }
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

        dadosHome.addProperty("world", location.getWorld().getName());
        dadosHome.addProperty("x", location.getX());
        dadosHome.addProperty("y", location.getY());
        dadosHome.addProperty("z", location.getZ());

        // Cria ou atualiza a home com esse nome.
        homesPlayer.add(nomeHome, dadosHome);
        player.sendMessage("§5§l[Nxo§c§lPixel] §f Localização salva com sucesso!. \n");

        salvarArquivo();
    }

    public void home(Player player, String nomeHome) {
        String uuid = uuid(player);
        Boolean getCond = hasHome(player);

        if(getCond == false) {
            JsonObject homesPlayer = homes.getAsJsonObject(uuid);
            JsonObject homesObject = homesPlayer.getAsJsonObject(nomeHome);
            World world = Bukkit.getWorld(homesObject.get("world").getAsString());
            String x = homesObject.get("x").getAsString();
            String y = homesObject.get("y").getAsString();
            String z = homesObject.get("z").getAsString();
            double xDouble = Double.parseDouble(x);
            double yDouble = Double.parseDouble(y);
            double zDouble = Double.parseDouble(z);
            Location toHome = new Location(world,xDouble,yDouble,zDouble);
            player.teleport(toHome);
        }
    }

    public void homes(Player player) {
        String uuid = uuid(player);
        int i;
        Boolean getCond = hasHome(player);

        if(getCond == false) {
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
        Boolean getCond = hasHome(player);

        if(getCond == false) {
            JsonObject homesPlayer = homes.getAsJsonObject(uuid);
            List<String> keys = new ArrayList<>((homesPlayer.keySet()));
            for(i = 0; i < homesPlayer.size(); i++) {
                String homeName = keys.get(i);
                if(Objects.equals(homeName, home)) {
                    homesPlayer.remove(home);
                }
            }
            salvarArquivo();
        }
    }

    public void renameHome(Player player, String oldName, String newName) {
        String uuid = uuid(player);
        int i;
        Boolean getCond = hasHome(player);

        if(getCond == false) {
            JsonObject homesPlayer = homes.getAsJsonObject(uuid);
            List<String> keys = new ArrayList<>((homesPlayer.keySet()));
            for(i = 0; i < homesPlayer.size(); i++) {
                String homeName = keys.get(i);
                if(Objects.equals(oldName, homeName)) {
                    JsonElement newHome = homesPlayer.remove(oldName);
                    if(newHome != null) {
                        homesPlayer.add(newName, newHome);
                        player.sendMessage("§5§l[Nxo§c§lPixel] §fNome da home alterado com sucesso!.");
                        salvarArquivo();
                    }
                }
            }
        }
    }

    private void salvarArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(homes, writer);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao salvar homes.json", e
            );
        }
    }
}
