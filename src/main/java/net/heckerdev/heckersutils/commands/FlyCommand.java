package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("fly")
@Description("Enable/disable flight mode.")
public class FlyCommand extends BaseCommand {

    @Default
    @Syntax("(optional) <player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.fly")) {
            sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Flight mode successfully disabled!");
                } else {
                    player.setAllowFlight(true);
                    sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Flight mode successfully enabled!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You need to specify a player to enable/disable flight mode for!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /fly " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
                sender.sendMessage(ChatColor.YELLOW + "You can also just use " + ChatColor.UNDERLINE + "/fly" + ChatColor.RESET + ChatColor.YELLOW + " to enable/disable flight mode for yourself, but you need to be a player to do that!" + ChatColor.RESET + ChatColor.GRAY + " Usage: " + ChatColor.UNDERLINE + "/fly");
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.fly.others")) {
                sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command like this!");
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    if (player.getAllowFlight()) {
                        player.setAllowFlight(false);
                        player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "❌" + ChatColor.RESET + ChatColor.RED + " Flight mode disabled!");
                        sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Flight mode successfully disabled for " + args[0] + "!");
                    } else {
                        player.setAllowFlight(true);
                        player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Flight mode enabled!");
                        sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Flight mode successfully enabled for " + args[0] + "!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + args[0] + " is not a valid player! - Make sure the player is online!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /fly " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
                }
            }
        }
    }
}
