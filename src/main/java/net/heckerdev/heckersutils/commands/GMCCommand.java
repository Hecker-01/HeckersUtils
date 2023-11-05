package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("gmc")
@Description("Sets Gamemode to creative.")
public class GMCCommand extends BaseCommand {

    @Default
    @Syntax("(optional) <player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.gmc")) {
            sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Gamemode successfully switched to creative!");
            } else {
                sender.sendMessage(ChatColor.RED + "You need to specify a player to switch to creative!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /gmc " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
                sender.sendMessage(ChatColor.YELLOW + "You can also just use " + ChatColor.UNDERLINE + "/gmc" + ChatColor.RESET + ChatColor.YELLOW + " to set your own gamemode to creative, but you need to be a player to do that!" + ChatColor.RESET + ChatColor.GRAY + " Usage: " + ChatColor.UNDERLINE + "/gmc");
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.gmc.others")) {
                sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command like this!");
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " " + args[0] + "'s gamemode successfully switched to creative!");
                    player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Your gamemode has been switched to creative!");
                } else {
                    sender.sendMessage(ChatColor.RED + args[0] + " is not a valid player! - Make sure the player is online!" + ChatColor.RESET + ChatColor.GRAY + " Usage: /gmc " + ChatColor.UNDERLINE + "<player>" + ChatColor.RESET);
                }
            }
        }
    }
}
