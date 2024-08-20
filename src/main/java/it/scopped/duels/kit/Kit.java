package it.scopped.duels.kit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Kit {
    private String name;
    private ItemStack displayItem;
    private List<ItemStack> items;
    private ItemStack[] armor;

    public void give(Player player) {
        PlayerInventory inventory = player.getInventory();

        inventory.clear();
        inventory.setArmorContents(armor);

        items.forEach(inventory::addItem);
    }
}