package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("tpall")
@Description("Teleport all players to you.")
public class TPAllCommand extends BaseCommand {

    @Default
    @Syntax("")
    @CommandCompletion("")
    public void onDefault(@NotNull CommandSender sender) {
        if (!sender.hasPermission("heckersutils.command.tpall")) {
            sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.teleport((Player) sender);
            }
            sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "✔" + ChatColor.RESET + ChatColor.GREEN + " Successfully teleported all players to you!");
        }
    }
}
