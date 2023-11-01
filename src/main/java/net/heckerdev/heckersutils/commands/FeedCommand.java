package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("feed")
@Description("Feed yourself.")
public class FeedCommand extends BaseCommand {

    @Default
    @Syntax("")
    @CommandCompletion("")
    public void onDefault(@NotNull CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "You can only execute this as a player!");
            return;
        }
        Player player = (Player) sender;
        if (!sender.hasPermission("heckersutils.command.feed")) {
            player.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else {
            player.setFoodLevel(20);
            sender.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "✔" + ChatColor.RESET + ChatColor.GREEN + " You have been fed!");
        }
    }
}
