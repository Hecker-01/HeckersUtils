package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("showitem|show")
@Description("Show the item in your hand.")
public class ShowItemCommand extends BaseCommand {

    @Default
    @Syntax("")
    @CommandCompletion("")
    public void onDefault(@NotNull CommandSender sender) {
        if (!sender.hasPermission("heckersutils.command.showitem")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ComponentLike item = (ComponentLike) player.getInventory().getItemInMainHand();
                //broadcast to all players;
                String playerName = "%vault_prefix%%player_name%%vault_suffix%";
                playerName = PlaceholderAPI.setPlaceholders(player, playerName);
                Bukkit.getServer().broadcast(MiniMessage.miniMessage().deserialize(playerName + "<white> is holding " + item + "<white>!"));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to be a player to use this command!"));
            }
        }
    }
}
