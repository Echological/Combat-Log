//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package page.echology.combatlog.utils;

import page.echology.combatlog.CombatLog;

import java.util.List;

public enum Config {
    ACTION_COMMANDS("Action.commands"),
    ACTION_KILL("Action.kill"),
    ACTION_CHAT("Action.chat"),
    MESSAGE_ENTER_AB("Combat.enter.action-bar"),
    MESSAGE_ENTER_CHAT("Combat.enter.chat"),
    MESSAGE_UPDATE_AB("Combat.update.action-bar"),
    MESSAGE_UPDATE_CHAT("Combat.update.chat"),
    MESSAGE_OUT_AB("Combat.out.action-bar"),
    MESSAGE_OUT_CHAT("Combat.out.chat"),
    UPDATE_INTERVAL("update-interval"),
    COMBAT_TIME("combat-time"),
    DISABLED_CMDS("Combat.disabled-commands.commands"),
    DISABLED_CMDS_MESSAGE("Combat.disabled-commands.message");


    private final String v;

    private Config(String k) {
        this.v = k;
    }

    public double getDouble() {
        return CombatLog.instance().getConfig().getDouble(this.v);
    }

    public int getInt() {
        return CombatLog.instance().getConfig().getInt(this.v);
    }

    public long getLong() {
        return CombatLog.instance().getConfig().getLong(this.v);
    }

    public String getText() {
        return CombatLog.instance().getConfig().getString(this.v);
    }

    public List<?> getList() {
        return CombatLog.instance().getConfig().getList(this.v);
    }

    public List<String> getStringList() {
        return CombatLog.instance().getConfig().getStringList(this.v);
    }

    public boolean getBoolean() {
        return CombatLog.instance().getConfig().getBoolean(this.v);
    }
}
