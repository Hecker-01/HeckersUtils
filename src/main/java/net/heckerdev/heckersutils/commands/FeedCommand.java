package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("feed")
@Description("Feed yourself.")
public class FeedCommand extends BaseCommand {

    @Default
    @Syntax("(optional) <player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.feed")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> You have been successfully fed!"));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to specify a player to feed!<gray> Usage: /feed <u><player></u>"));
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<yellow>You can also just use <u>/feed</u> to feed yourself, but you need to be a player to do that!<gray> Usage: <u>/feed</u>"));
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.feed.others")) {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command like this!"));
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setFoodLevel(20);
                    player.setSaturation(20);
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> " + args[0] + " has been successfully fed!"));
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> You have been successfully fed!"));
                } else {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>" + args[0] + " is not a valid player! - Make sure the player is online!<gray> Usage: /feed <u><player></u>"));
                }
            }
        }
    }
}
