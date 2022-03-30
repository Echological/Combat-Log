package page.echology.combatlog.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import page.echology.combatlog.utils.Config;
import page.echology.combatlog.utils.Log;
import page.echology.combatlog.utils.Utils;

import java.util.List;
import java.util.logging.Logger;

public class PlayerEvents implements Listener {

    @EventHandler
    public void dmg(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player)event.getDamager();
            if (event.getEntity() instanceof Player) {
                Player victim = (Player)event.getEntity();
                Log.log(attacker);
                Log.log(victim);
            }
        }
    }

    @EventHandler
    public void leave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Log.inCombat(player)) {
            Log.combatLog(player);
        }
    }

    @EventHandler
    public void cmd(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (Log.inCombat(player)) {
            String cmd = event.getMessage();
            cmd = cmd.split(" ")[0];
            cmd = cmd.replaceFirst("/", "");
            List<String> dcmds = Config.DISABLED_CMDS.getStringList();
            if (dcmds.contains(cmd) || dcmds.contains("/"+cmd)) {
                event.setCancelled(true);
                String ds = Config.DISABLED_CMDS_MESSAGE.getText();
                ds = ds.replaceAll("%command%", cmd);
                player.sendMessage(Utils.colored(ds));
            }
        }

    }

}
