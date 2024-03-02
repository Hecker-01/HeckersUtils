package net.heckerdev.heckersutils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("workbench|wb|craft|ct")
@Description("Open the workbench.")
public class WorkbenchCommand extends BaseCommand {

    @Default
    @Syntax("")
    @CommandCompletion("")
    public void onDefault(@NotNull CommandSender sender) {
        if (!sender.hasPermission("heckersutils.command.craft") && !sender.hasPermission("heckersutils.command.workbench")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>⚠ You do not have permission to use this command!"));
        } else {
            Player player = (Player) sender;
            player.openWorkbench(null, true);
        }
    }
}
