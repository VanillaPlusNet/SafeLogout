package dev.goldenedit.safelogout;

import org.bukkit.plugin.java.JavaPlugin;

public final class SafeLogout extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("logout").setExecutor(new LogoutCommand(this));
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }
}
