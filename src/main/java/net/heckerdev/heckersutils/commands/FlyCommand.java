package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("fly")
@Description("Enable/disable flight mode.")
public class FlyCommand extends BaseCommand {

    @Default
    @Syntax("")
    @CommandCompletion("")
    public void onDefault(@NotNull CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "You can only execute this as a player!");
            return;
        }
        Player player = (Player) sender;
        if (!sender.hasPermission("heckersutils.command.fly")) {
            player.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                sender.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "✔" + ChatColor.RESET + ChatColor.GREEN + " Flight mode successfully disabled!");
            } else {
                player.setAllowFlight(true);
                sender.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "✔" + ChatColor.RESET + ChatColor.GREEN + " Flight mode successfully enabled!");
            }
        }
    }
}
