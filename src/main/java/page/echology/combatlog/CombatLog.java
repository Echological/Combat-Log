//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package page.echology.combatlog;

import com.github.Echological.MinehutService.MinehutServer;
import com.github.Echological.MinehutService.MinehutService;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import page.echology.combatlog.commands.CombatCmd;
import page.echology.combatlog.listeners.PlayerEvents;
import page.echology.combatlog.utils.Config;
import page.echology.combatlog.utils.Log;

public final class CombatLog extends JavaPlugin {
    static CombatLog instance;
    static String SERVER_ID;
    static String SERVER_NAME;
    static boolean ONLINE;

    public void onEnable() {
        Logger.getLogger("Minecraft").info("Hello There! <3");
        SERVER_ID = MinehutServer.id();
        SERVER_NAME = MinehutServer.name();
        ONLINE = MinehutServer.online();

        if (SERVER_ID != null && SERVER_NAME != null && ONLINE) {
            Logger.getLogger("Minecraft").info("Running on Minehut server \"" + SERVER_NAME + "\"");
        } else {
            Logger.getLogger("Minecraft").severe("You can not use this plugin outside of Minehut, if this is an issue, contact the developer.");
            setEnabled(false);
        }

        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginCommand("combatlog").setExecutor(new CombatCmd());
        int interval = Config.UPDATE_INTERVAL.getInt();
        Bukkit.getScheduler().runTaskTimer(this, Log::update, 0L, interval);
    }

    public void onDisable() {
        Logger.getLogger("Minecraft").info("Bye! <3");
    }

    public static CombatLog instance() {
        return instance;
    }
}
