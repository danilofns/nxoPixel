package org.nxo.nxoPixel.nxoPixel.Chat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent cEvent) {
        Player player = cEvent.getPlayer();
        String display = player.getDisplayName();
        String getMensagem = cEvent.getMessage();
        String rico = "§6⭐ ";
        if(getMensagem.contains("&")) {
            String Mensagem = getMensagem.replace("&","§");
            String Name = cEvent.getPlayer().getName();
            cEvent.setFormat(rico + display + Name + ": §7 " + Mensagem);
        } else {
            String Name = cEvent.getPlayer().getName();
            cEvent.setFormat(rico + display + Name + ": §7 " + getMensagem);
        }
    }
}
