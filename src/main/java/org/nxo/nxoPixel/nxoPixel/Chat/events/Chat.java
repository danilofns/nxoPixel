package org.nxo.nxoPixel.nxoPixel.Chat.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent cEvent) {
        String Mensagem = cEvent.getMessage();
        String Name = cEvent.getPlayer().getName();
        cEvent.setFormat(" "+ Name + ": §7 " + Mensagem);
    }
}
