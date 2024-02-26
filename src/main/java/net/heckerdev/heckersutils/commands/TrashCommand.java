package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

@CommandAlias("trash")
@Description("Open the tashbin.")
public class TrashCommand extends BaseCommand {

    @Default
    @Syntax("")
    @CommandCompletion("")
    public void onDefault(@NotNull CommandSender sender) {
        if (!sender.hasPermission("heckersutils.command.trash")) {
            sender.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else {
            Player player = (Player) sender;
            Inventory trash = Bukkit.createInventory(player, InventoryType.DISPENSER, Component.text(ChatColor.WHITE + "Trash bin, close to empty"));
            player.openInventory(trash);
        }
    }
}
