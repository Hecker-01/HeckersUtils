package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("heal")
@Description("Heal yourself.")
public class HealCommand extends BaseCommand {

    @Default
    @Syntax("")
    @CommandCompletion("")
    public void onDefault(@NotNull CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "You can only execute this as a player!");
            return;
        }
        Player player = (Player) sender;
        if (!sender.hasPermission("heckersutils.command.heal")) {
            player.sendMessage(ChatColor.RED + "⚠ You do not have permission to use this command!");
        } else {
            double maxHealth = player.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getValue();
            player.setHealth(maxHealth);
            sender.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "✔" + ChatColor.RESET + ChatColor.GREEN + " You have been healed!");
        }
    }
}
