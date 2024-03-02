package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("god")
@Description("Enable/disable god mode.")
public class GodCommand extends BaseCommand {

    @Default
    @Syntax("(optional) <player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.god")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.isInvulnerable()) {
                    player.setInvulnerable(false);
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> God mode successfully disabled!"));
                } else {
                    player.setInvulnerable(true);
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> God mode successfully enabled!"));
                }
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to specify a player to enable/disable god mode for!<gray> Usage: /god <u><player></u>"));
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<yellow>You can also just use <u>/god</u> to enable/disable god mode for yourself, but you need to be a player to do that!"));
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.god.others")) {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command like this!"));
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    if (player.isInvulnerable()) {
                        player.setInvulnerable(false);
                        player.sendMessage(MiniMessage.miniMessage().deserialize("<red><b>❌</b> God mode disabled!"));
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> God mode successfully disabled for " + args[0] + "!"));
                    } else {
                        player.setInvulnerable(true);
                        player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> God mode enabled!"));
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> God mode successfully enabled for " + args[0] + "!"));
                    }
                } else {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>" + args[0] + " is not a valid player! - Make sure the player is online!<gray> Usage: /god <u><player></u>"));
                }
            }
        }
    }
}
