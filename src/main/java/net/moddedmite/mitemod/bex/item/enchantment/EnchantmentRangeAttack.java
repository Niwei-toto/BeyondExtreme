package net.moddedmite.mitemod.bex.item.enchantment;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.item.ItemInfinitySword;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class EnchantmentRangeAttack extends Enchantment {
    public EnchantmentRangeAttack(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }
    @Override
    public int getNumLevels() {
        return 1;
    }

    @Override
    public String getNameSuffix() {
        return "range";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemInfinitySword;
    }

    @Override
    public boolean isOnCreativeTab(CreativeTabs creativeTabs) {
        return creativeTabs == BEXItems.tabBEX;
    }
}