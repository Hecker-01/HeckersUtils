package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if (itemStack.getType().isAir()) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You are not holding anything!"));
                    return;
                }
                Component itemName;
                if (itemStack.getItemMeta() != null && itemStack.getItemMeta().hasDisplayName()) {
                    itemName = itemStack.getItemMeta().displayName();
                } else {
                    String defaultName = itemStack.getType().name().replace("_", " ").toLowerCase();
                    StringBuilder formattedName = new StringBuilder();
                    for (String word : defaultName.split(" ")) {
                        formattedName.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
                    }
                    if (!itemStack.getItemMeta().hasEnchants()) {
                        itemName = Component.text(formattedName.toString().trim()).color(TextColor.color(0xFFFFFF));
                    } else {
                        itemName = Component.text(formattedName.toString().trim()).color(TextColor.color(0x55FFFF));
                    }
                }
                Component item = Component.text("[").color(TextColor.color(0x55FFFF)).append(itemName.hoverEvent(itemStack)).append(Component.text("]").color(TextColor.color(0x55FFFF)));
                String playerName = "%vault_prefix%%player_name%%vault_suffix%";
                playerName = PlaceholderAPI.setPlaceholders(player, playerName);
                playerName = playerName.replace("&0", "<black>").replace("&1", "<dark_blue>").replace("&2", "<dark_green>").replace("&3", "<dark_aqua>").replace("&4", "<dark_red>").replace("&5", "<dark_purple>").replace("&6", "<gold>").replace("&7", "<gray>").replace("&8", "<dark_gray>").replace("&9", "<blue>").replace("&a", "<green>").replace("&b", "<aqua>").replace("&c", "<red>").replace("&d", "<light_purple>").replace("&e", "<yellow>").replace("&f", "<white>").replace("&r", "<reset>").replace("&l", "<bold>").replace("&o", "<italic>").replace("&n", "<underlined>").replace("&m", "<strikethrough>").replace("&k", "<obfuscated>");
                Component text = MiniMessage.miniMessage().deserialize("<white>" + playerName + " is holding ").append(item).append(Component.text("!").color(TextColor.color(0xFFFFFF)));
                Bukkit.getServer().broadcast(text);
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You need to be a player to use this command!"));
            }
        }
    }
}
