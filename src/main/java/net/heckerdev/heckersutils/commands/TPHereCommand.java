package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
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
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to specify a player to tp you!<gray> Usage: /tphere <u><player></u>"));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("You must be a player to use this command!"));
            }
        } else {
            Player player = Bukkit.getPlayer(args[0]);
            if (player != null) {
                player.teleport((Player) sender);
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> Successfully teleported " + args[0] + " to you!"));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>" + args[0] + " is not a valid player! - Make sure the player is online!<gray> Usage: /tphere <u><player></u>"));
            }
        }
    }
}
