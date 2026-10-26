package org.nxo.nxoPixel.nxoPixel.tags;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class playerFoolder {

    private final File playerArquivo;
    private final File pluginFoolder;
    private final File pluginNewFoolder;

    public JsonObject playerJson;
    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public playerFoolder(JavaPlugin plugin) {
        this.pluginFoolder = plugin.getDataFolder();
        this.pluginNewFoolder = new File(pluginFoolder, "player");
        this.playerArquivo = new File(pluginNewFoolder, "tags.json");

        if (!pluginNewFoolder.exists()) {
            pluginNewFoolder.mkdirs();
        }

        tagsLoader();

    }


    public boolean playerArquivoExiste() {
        return playerArquivo.exists();
    }

    public void tagsLoader() {
        if (playerArquivoExiste()){
            try (FileReader reader = new FileReader(playerArquivo)) {
                playerJson = gson.fromJson(reader, JsonObject.class);

                if (playerJson == null) {
                    playerJson = new JsonObject();
                }
            } catch (IOException e){
                throw new RuntimeException(
                        "Erro ao carregar tags.json", e
                );
            }
        } else {
            playerJson = new JsonObject();
            saveTags();
        }
    }

    public void saveTags() {
        try (FileWriter writer = new FileWriter(playerArquivo)) {
            gson.toJson(playerJson, writer);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao salvar tags.json", e
            );
        }
    }

    public JsonObject getPlayerJson() {
        return playerJson;
    }
}
