package page.echology.combatlog.utils;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Log {
    static Map<UUID, Integer> log = new HashMap<>();

    public static void update() {
        Iterator<UUID> it = log.keySet().iterator();

        while(it.hasNext()) {
            UUID uuid = it.next();
            int time = log.get(uuid);
            int d = time - 20;
            Player p = Bukkit.getPlayer(uuid);
            if (d < 0) {
                displayOutLog(p);
                it.remove();
            } else {
                displayUpdateLog(p);
                log.put(uuid, d);
            }
        }

    }

    static void displayOutLog(Player player) {
        String ab = Config.MESSAGE_OUT_AB.getText();
        String chat = Config.MESSAGE_OUT_CHAT.getText();
        displayLog(player, ab, chat);
    }

    static void displayUpdateLog(Player player) {
        String ab = Config.MESSAGE_UPDATE_AB.getText();
        String chat = Config.MESSAGE_UPDATE_CHAT.getText();
        displayLog(player, ab, chat);
    }

    static void displayEnterLog(Player player) {
        String ab = Config.MESSAGE_ENTER_AB.getText();
        String chat = Config.MESSAGE_ENTER_CHAT.getText();
        displayLog(player, ab, chat);
    }

    static void displayLog(Player player, String ab, String chat) {
        if (log == null) {
            log = new HashMap<>();
        }

        UUID uuid = player.getUniqueId();
        if (log.containsKey(uuid)) {
            int interval = log.get(uuid) / 20;
            if (ab != null && !ab.trim().equals("")) {
                ab = ab.replaceAll("%time%", String.valueOf(interval));
                ab = Utils.colored(ab);
                Utils.actionBar(player, ab);
            }

            if (chat != null && !chat.trim().equals("")) {
                chat = chat.replaceAll("%time%", String.valueOf(interval));
                chat = Utils.colored(chat);
                player.sendMessage(chat);
            }

        }
    }

    public static void log(Player player) {
        log(player.getUniqueId());
    }

    public static void log(UUID uuid) {
        if (log == null) {
            log = new HashMap<>();
        }

        int time = Config.COMBAT_TIME.getInt();
        if (!log.containsKey(uuid)) {
            displayEnterLog(Bukkit.getPlayer(uuid));
        }

        log.put(uuid, time);
    }

    public static boolean inCombat(UUID uuid) {
        if (log == null) {
            log = new HashMap<>();
        }

        return log.containsKey(uuid) && log.get(uuid) > 0;
    }

    public static boolean inCombat(Player player) {
        return inCombat(player.getUniqueId());
    }

    public static void combatLog(Player player) {
        UUID uuid = player.getUniqueId();
        if (inCombat(uuid)) {
            boolean kill = Config.ACTION_KILL.getBoolean();
            if (kill) {
                player.setHealth(0.0D);
            }

            List<String> commands = Config.ACTION_COMMANDS.getStringList();
            commands.forEach((cmd) -> {
                if (cmd != null && !cmd.trim().equals("")) {
                    cmd = cmd.replaceAll("%player%", player.getName());
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
                }

            });
            log.remove(uuid);
            String chat = Config.ACTION_CHAT.getText();
            if (chat != null && !chat.trim().equals("")) {
                chat = chat.replaceAll("%player%", player.getName());
                chat = Utils.colored(chat);
                Bukkit.broadcastMessage(chat);
            }

        }
    }
}
