package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("gms")
@Description("Sets Gamemode to survival.")
public class GMSCommand extends BaseCommand {

    @Default
    @Syntax("(optional) <player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.gms")) {
            sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Gamemode successfully switched to survival!");
            } else {
                sender.sendMessage(ChatColor.RED + "You need to specify a player to switch to survival!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /gms " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
                sender.sendMessage(ChatColor.YELLOW + "You can also just use " + ChatColor.UNDERLINE + "/gms" + ChatColor.RESET + ChatColor.YELLOW + " to set your own gamemode to survival, but you need to be a player to do that!" + ChatColor.RESET + ChatColor.GRAY + " Usage: " + ChatColor.UNDERLINE + "/gms");
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.gms.others")) {
                sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command like this!");
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " " + args[0] + "'s gamemode successfully switched to survival!");
                    player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Your gamemode has been switched to survival!");
                } else {
                    sender.sendMessage(ChatColor.RED + args[0] + " is not a valid player! - Make sure the player is online!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /gms " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
                }
            }
        }
    }
}
