package net.moddedmite.mitemod.bex.item.enchantment;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class EnchantmentForge extends Enchantment {
    public EnchantmentForge(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    public int getNumLevels() {
        return 5;
    }

    public String getNameSuffix() {
        return "forge";
    }

    public boolean canEnchantItem(Item item) {
        return item instanceof ItemAxe || item instanceof ItemWarHammer;
    }

    public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
        return creativeModeTab == BEXItems.tabBEX;
    }
}

