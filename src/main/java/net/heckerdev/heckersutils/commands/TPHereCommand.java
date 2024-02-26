package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("tphere")
@Description("Teleport a player to you.")
public class TPHereCommand extends BaseCommand {

    @Default
    @Syntax("<player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.tphere")) {
            sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.RED + "You need to specify a player to tp you!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /tphere " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
            } else {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command!" + ChatColor.RESET);
            }
        } else {
            Player player = Bukkit.getPlayer(args[0]);
            if (player != null) {
                player.teleport((Player) sender);
                sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Successfully teleported " + args[0] + " to you!");
            } else {
                sender.sendMessage(ChatColor.RED + args[0] + " is not a valid player! - Make sure the player is online!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /tphere " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
            }
        }
    }
}
