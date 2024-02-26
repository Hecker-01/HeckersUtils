package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("enderchest|echest|ec")
@Description("Open your enderchest.")
public class EnderChestCommand extends BaseCommand {

    @Default
    @Syntax("")
    @CommandCompletion("")
    public void onDefault(@NotNull CommandSender sender) {
        if (!sender.hasPermission("heckersutils.command.enderchest")) {
            sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else {
            Player player = (Player) sender;
            player.openInventory(player.getEnderChest());
        }
    }
}
