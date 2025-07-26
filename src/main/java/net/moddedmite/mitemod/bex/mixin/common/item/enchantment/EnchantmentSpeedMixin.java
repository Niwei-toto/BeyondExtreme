package net.moddedmite.mitemod.bex.mixin.common.item.enchantment;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EnchantmentSpeed.class)
public abstract class EnchantmentSpeedMixin extends Enchantment implements ITEEnchantment {
    protected EnchantmentSpeedMixin(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    public int getNumLevelsForVibranium() {
        return 7;
    }
}
