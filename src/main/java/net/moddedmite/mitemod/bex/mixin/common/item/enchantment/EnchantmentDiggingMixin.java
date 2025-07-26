package net.moddedmite.mitemod.bex.mixin.common.item.enchantment;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(value = EnchantmentDigging.class)
public abstract class EnchantmentDiggingMixin extends Enchantment implements ITEEnchantment {
    protected EnchantmentDiggingMixin(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @SoftOverride
    public int getNumLevelsForVibranium() {
        return 7;
    }
}
