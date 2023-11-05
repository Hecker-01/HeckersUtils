package net.heckerdev.heckersutils;

import co.aikar.commands.PaperCommandManager;
import net.heckerdev.heckersutils.commands.*;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeckersUtils extends JavaPlugin {

    private static Permission perms = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setupCommands();
        setupPermissions();
        Bukkit.getLogger().info("HeckersUtils has been enabled!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("HeckersUtils has been disabled!");
    }

    private void setupCommands() {
        // Registering commands.
        PaperCommandManager manager= new PaperCommandManager(this);
        manager.registerCommand(new GMCCommand());
        manager.registerCommand(new GMSCommand());
        manager.registerCommand(new GMACommand());
        manager.registerCommand(new GMSPCommand());
        manager.registerCommand(new HealCommand());
        manager.registerCommand(new FeedCommand());
        manager.registerCommand(new FlyCommand());
    }

    private boolean setupPermissions() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().warning(" - Disabled because Vault is not installed!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null) {
            return false;
        }
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Permission getPermissions() {
        return perms;
    }
}
