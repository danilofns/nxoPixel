package org.nxo.nxoPixel.nxoPixel.tags;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class tagsFoolder {

    private final File tagsArquivo;
    private final File pluginFoolder;
    private final File pluginNewFoolder;

    public JsonObject tags;
    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public tagsFoolder(JavaPlugin plugin) {
        this.pluginFoolder = plugin.getDataFolder();
        this.pluginNewFoolder = new File(pluginFoolder, "tags");
        this.tagsArquivo = new File(pluginNewFoolder, "tags.json");

        if (!pluginNewFoolder.exists()) {
            pluginNewFoolder.mkdirs();
        }

        tagsLoader();

    }


    public boolean tagsArquivoExiste() {
        return tagsArquivo.exists();
    }

    public void tagsLoader() {
        if (tagsArquivoExiste()){
            try (FileReader reader = new FileReader(tagsArquivo)) {
                tags = gson.fromJson(reader, JsonObject.class);

                if (tags == null) {
                    tags = new JsonObject();
                }
            } catch (IOException e){
                throw new RuntimeException(
                        "Erro ao carregar tags.json", e
                );
            }
        } else {
            tags = new JsonObject();
            saveTags();
        }
    }

    public void saveTags() {
        try (FileWriter writer = new FileWriter(tagsArquivo)) {
            gson.toJson(tags, writer);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao salvar tags.json", e
            );
        }
    }

    public JsonObject getTags() {
        return tags;
    }
}
