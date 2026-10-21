package org.nxo.nxoPixel.nxoPixel.essentialsCommands.home;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class homeManager {

    private final File arquivo;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public JsonObject homes;

    public homeManager(JavaPlugin plugin) {
        this.arquivo = new File(plugin.getDataFolder(), "homes.json");

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        carregarHomes();
    }

    public boolean arquivoExiste() {
        return arquivo.exists();
    }

    private void carregarHomes() {
        if (arquivoExiste()) {
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

    void salvarArquivo() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(homes, writer);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao salvar homes.json", e
            );
        }
    }

    public JsonObject getHomes() {
        return homes;
    }

}
