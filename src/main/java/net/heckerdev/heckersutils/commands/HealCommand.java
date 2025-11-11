package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@CommandAlias("heal")
@Description("Heal yourself.")
public class HealCommand extends BaseCommand {

    @Default
    @Syntax("(optional) <player>")
    @CommandCompletion("@players")
    public void onDefault(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("heckersutils.command.heal")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                double maxHealth = player.getMaxHealth();
                player.setHealth(maxHealth);
                player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> You have been successfully healed!"));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to specify a player to heal! <gray>Usage: /heal <u><player></u>"));
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<yellow>You can also just use <u>/heal</u> to heal yourself, but you need to be a player to do that! <gray>Usage: <u>/heal</u>"));
            }
        } else {
            if (!sender.hasPermission("heckersutils.command.heal.others")) {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command like this!"));
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    double maxHealth = player.getMaxHealth();
                    player.setHealth(maxHealth);
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> " + args[0] + " has been successfully healed!"));
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<green><b>✔</b> You have been successfully healed!"));
                } else {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>" + args[0] + " is not a valid player! - Make sure the player is online! <gray>Usage: /heal <u><player></u>"));
                }
            }
        }
    }
}
