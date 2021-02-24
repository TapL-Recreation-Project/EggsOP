package me.swipez.eggsop;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class EggListener implements Listener {

    Eggsop plugin;

    public EggListener(Eggsop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerFall(PlayerEggThrowEvent e){
        if (plugin.gamestarted) {
            List<String> mats = plugin.getConfig().getStringList("fallitems");
            List<String> counts = plugin.getConfig().getStringList("fallitemscount");
            List<String> enchants = plugin.getConfig().getStringList("enchantslist");
            List<String> peffects = plugin.getConfig().getStringList("peffects");
            int min = 0;
            int max = mats.size() - 1;
            double random = Math.random() * (max - min + 1) + min;
            int stackcount = Integer.parseInt(counts.get((int) random));
            ItemStack ritem = new ItemStack(Material.valueOf(mats.get((int) random).toUpperCase()), stackcount);
            if (ritem.getType() == Material.ENCHANTED_BOOK) {
                ItemMeta meta = ritem.getItemMeta();
                EnchantmentStorageMeta emeta = (EnchantmentStorageMeta) meta;
                int mine = 0;
                int maxe = enchants.size() - 1;
                double rench = Math.random() * (maxe - mine + 1) + mine;
                emeta.addStoredEnchant(Enchantment.getByKey(NamespacedKey.minecraft(enchants.get((int) rench).toLowerCase())), 10, true);
                ritem.setItemMeta(emeta);
            }
            if (ritem.getType() == Material.POTION) {
                ItemMeta meta = ritem.getItemMeta();
                PotionMeta pmeta = (PotionMeta) meta;
                int mine = 0;
                int maxe = peffects.size() - 1;
                double rench = Math.random() * (maxe - mine + 1) + mine;
                pmeta.addCustomEffect(new PotionEffect((PotionEffectType.getByName(peffects.get((int) rench).toUpperCase())), 1200, 2), true);
                pmeta.setColor(Color.YELLOW);
                pmeta.setDisplayName("OP Potion");
                ritem.setItemMeta(pmeta);
            }
            e.getEgg().getWorld().dropItemNaturally(e.getEgg().getLocation(), ritem);
        }
    }
}

