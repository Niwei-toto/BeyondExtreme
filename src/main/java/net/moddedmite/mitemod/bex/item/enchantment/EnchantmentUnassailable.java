package net.moddedmite.mitemod.bex.item.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.moddedmite.mitemod.bex.item.ItemInfinityArmor;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class EnchantmentUnassailable extends Enchantment {
    public EnchantmentUnassailable(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }
    @Override
    public int getNumLevels() {
        return 1;
    }

    @Override
    public String getNameSuffix() {
        return "unassailable";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemInfinityArmor;
    }

    @Override
    public boolean isOnCreativeTab(CreativeTabs creativeTabs) {
        return creativeTabs == BEXItems.tabBEX;
    }
}
