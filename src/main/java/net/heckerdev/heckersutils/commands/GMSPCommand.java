package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("gmsp")
@Description("Sets Gamemode to spectator.")
public class GMSPCommand extends BaseCommand {

    @Default
    @Syntax("(optional) <player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.gmsp")) {
            sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Gamemode successfully switched to spectator!");
            } else {
                sender.sendMessage(ChatColor.RED + "You need to specify a player to switch to spectator!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /gms " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
                sender.sendMessage(ChatColor.YELLOW + "You can also just use " + ChatColor.UNDERLINE + "/gmsp" + ChatColor.RESET + ChatColor.YELLOW + " to set your own gamemode to spectator, but you need to be a player to do that!" + ChatColor.RESET + ChatColor.GRAY + " Usage: " + ChatColor.UNDERLINE + "/gmsp");
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.gmsp.others")) {
                sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command like this!");
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " " + args[0] + "'s gamemode successfully switched to spectator!");
                    player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Your gamemode has been switched to spectator!");
                } else {
                    sender.sendMessage(ChatColor.RED + args[0] + " is not a valid player! - Make sure the player is online!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /gmsp " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
                }
            }
        }
    }
}
