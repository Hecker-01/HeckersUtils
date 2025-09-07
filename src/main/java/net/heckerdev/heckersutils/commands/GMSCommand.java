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

@CommandAlias("gms")
@Description("Sets Gamemode to survival.")
public class GMSCommand extends BaseCommand {

    @Default
    @Syntax("(optional) <player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.gms")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Gamemode successfully switched to survival!"));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to specify a player to switch to survival!<gray> Usage: /gms <u><player></u>"));
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<yellow>You can also just use <u>/gms</u> to set your own gamemode to survival, but you need to be a player to do that!<gray> Usage: <u>/gms</u>"));
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.gms.others")) {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command like this!"));
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> " + args[0] + "'s gamemode successfully switched to survival!"));
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Your gamemode has been switched to survival!"));
                } else {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>" + args[0] + " is not a valid player! - Make sure the player is online!<gray> Usage: /gms <u><player></u>"));
                }
            }
        }
    }
}
