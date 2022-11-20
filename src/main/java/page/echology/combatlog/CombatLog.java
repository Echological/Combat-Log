//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package page.echology.combatlog;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import page.echology.combatlog.commands.CombatCmd;
import page.echology.combatlog.listeners.PlayerEvents;
import page.echology.combatlog.utils.Log;

import java.util.logging.Logger;

public final class CombatLog extends JavaPlugin {
    static CombatLog instance;

    public void onEnable() {
        Logger.getLogger("Minecraft").info("Hello There! <3");

        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginCommand("combatlog").setExecutor(new CombatCmd());
        Bukkit.getScheduler().runTaskTimer(this, Log::update, 0L, 20L);
    }

    public void onDisable() {
        Logger.getLogger("Minecraft").info("Bye! <3");
    }

    public static CombatLog instance() {
        return instance;
    }
}
