package net.moddedmite.mitemod.bex.mixin.common.item.enchantment;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(value = EnchantmentArrowDamage.class)
public abstract class EnchantmentArrowDamageMixin extends Enchantment implements ITEEnchantment {
    public EnchantmentArrowDamageMixin(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @SoftOverride
    public int getNumLevelsForVibranium() {
        return 7;
    }
}
