package page.echology.combatlog.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import page.echology.combatlog.CombatLog;
import page.echology.combatlog.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombatCmd implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(Utils.colored("&c&lInvalid Usage\n\n   &8- &e/combatlog reload &7- reloads the config file\n"));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            float start = System.currentTimeMillis();
            sender.sendMessage(Utils.colored("&aReloading config..."));
            CombatLog.instance().reloadConfig();
            sender.sendMessage(Utils.colored("&aReload complete! Took: "+(System.currentTimeMillis()/start)/1000+"s"));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            commands.add("reload");
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
