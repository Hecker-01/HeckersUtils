package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Flight mode successfully disabled!"));
                } else {
                    player.setAllowFlight(true);
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Flight mode successfully enabled!"));
                }
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to specify a player to enable/disable flight mode for!<gray> Usage: /fly <u><player></u>"));
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<yellow>You can also just use <u>/fly</u> to enable/disable flight mode for yourself, but you need to be a player to do that!<gray> Usage: <u>/fly</u"));
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.fly.others")) {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command like this!"));
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    if (player.getAllowFlight()) {
                        player.setAllowFlight(false);
                        player.sendMessage(MiniMessage.miniMessage().deserialize("<red><b>❌</b><red> Flight mode disabled!"));
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Flight mode successfully disabled for " + args[0] + "!"));
                    } else {
                        player.setAllowFlight(true);
                        player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Flight mode enabled!"));
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b><green> Flight mode successfully enabled for " + args[0] + "!"));
                    }
                } else {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>" + args[0] + " is not a valid player! - Make sure the player is online!<gray> Usage: /fly <u><player></u>"));
                }
            }
        }
    }
}
