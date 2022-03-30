//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package page.echology.combatlog.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    public static String colored(String ex) {
        return ChatColor.translateAlternateColorCodes('&', ex);
    }

    public static void actionBar(Player player, String ex) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ex));
    }
}
