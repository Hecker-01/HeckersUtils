package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Gamemode successfully switched to spectator!"));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to specify a player to switch to spectator!<gray> Usage: /gmsp <u><player></u>"));
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<yellow>You can also just use <u>/gmsp</u> to set your own gamemode to spectator, but you need to be a player to do that!<gray> Usage: <u>/gmsp</u>"));
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.gmsp.others")) {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command like this!"));
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> " + args[0] + "'s gamemode successfully switched to spectator!"));
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Your gamemode has been switched to spectator!"));
                } else {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>" + args[0] + " is not a valid player! - Make sure the player is online!<gray> Usage: /gmsp <u><player></u>"));
                }
            }
        }
    }
}
