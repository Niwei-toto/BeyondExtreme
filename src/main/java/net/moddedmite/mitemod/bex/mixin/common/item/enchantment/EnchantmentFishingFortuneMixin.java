package net.moddedmite.mitemod.bex.mixin.common.item.enchantment;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.api.ITEEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(EnchantmentFishingFortune.class)
public abstract class EnchantmentFishingFortuneMixin extends Enchantment implements ITEEnchantment {
    protected EnchantmentFishingFortuneMixin(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @SoftOverride
    public int getNumLevelsForVibranium() {
      return 7;
    }
}

