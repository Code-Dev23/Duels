package it.scopped.duels.utility.strings;

import it.scopped.duels.nms.impl.ActionBar;
import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CC {

    public String translate(String message, Object... params) {
        return ChatColor.translateAlternateColorCodes('&', replace(message, params));
    }

    public List<String> translate(List<String> message) {
        return message.stream().map(CC::translate).collect(Collectors.toList());
    }

    public List<String> translate(List<String> message, Player player) {
        return message.stream().map(str -> CC.translate(player, str)).collect(Collectors.toList());
    }

    public String translate(Player player, String message, Object... params) {
        return PlaceholderAPI.setPlaceholders(player, translate(message, params));
    }

    public void send(@NotNull CommandSender sender, String message, Object... params) {
        sender.sendMessage(translate(message, params));
    }

    @SuppressWarnings("all")
    public void sendTitle(@NotNull Player player, String title, String subTitle) {
        player.sendTitle(translate(title), translate(subTitle));
    }

    public void sendActionBar(Player player, String message, Object... params) {
        ActionBar.sendActionBar(player, translate(message, params));
    }

    public String replace(String message, Object @NotNull ... params) {
        if (params.length % 2 != 0)
            throw new IllegalArgumentException("Parameters should be in key-value pairs.");

        for (int i = 0; i < params.length; i += 2)
            message = message.replace(params[i].toString(), params[i + 1].toString());

        return message;
    }
}