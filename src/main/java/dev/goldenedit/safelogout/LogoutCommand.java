package dev.goldenedit.safelogout;

import com.github.sirblobman.combatlogx.api.ICombatLogX;
import com.github.sirblobman.combatlogx.api.manager.ICombatManager;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class LogoutCommand implements CommandExecutor {
    private final Plugin plugin;

    public LogoutCommand(Plugin plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (isEnabled()) {

            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (isInCombat(p)) {
                    p.sendMessage("§cYou can't use /logout while in combat.");
                } else {
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("KickPlayer");
                    out.writeUTF(p.getName());
                    out.writeUTF("§aSafely Logged Out!");
                    p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                }
            } else {
                commandSender.sendMessage("Only players can do this!");
            }

        }
        return true;
    }
    public boolean isInCombat(Player player) {
        ICombatLogX plugin = getAPI();
        ICombatManager combatManager = plugin.getCombatManager();
        return combatManager.isInCombat(player);
    }

    public boolean isEnabled() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        return pluginManager.isPluginEnabled("CombatLogX");
    }

    public ICombatLogX getAPI() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        Plugin plugin = pluginManager.getPlugin("CombatLogX");
        return (ICombatLogX) plugin;
    }
}
