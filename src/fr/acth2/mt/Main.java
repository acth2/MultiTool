package fr.acth2.mt;

import fr.acth2.mt.components.MainWindow;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import javax.swing.*;

public class Main {
    private static final String CLIENT_ID = "1340484278575693905";

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Linked with " + user);

        DiscordRPC.discordInitialize(CLIENT_ID, handlers, false);

        DiscordRichPresence discordPresence = new DiscordRichPresence();
        discordPresence.state = "<- Look at the clock!!!";
        discordPresence.details = "Using the best software";
        discordPresence.startTimestamp = System.currentTimeMillis() / 1000;
        discordPresence.largeImageKey = "mts-bigger";
        discordPresence.largeImageText = "MTS!!!";
        discordPresence.smallImageKey = "cursor";
        discordPresence.smallImageText = "Lil cursor";
        discordPresence.partyId = "ae488379-351d-4a4f-ad32-2b9b01c91657";
        discordPresence.joinSecret = "MTI4NzM0OjFpMmhuZToxMjMxMjM=";

        DiscordRPC.discordUpdatePresence(discordPresence);

        new Thread(() -> {
            while (true) {
                DiscordRPC.discordRunCallbacks();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        new MainWindow(1);

        Runtime.getRuntime().addShutdownHook(new Thread(DiscordRPC::discordShutdown));
    }
}
