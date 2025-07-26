package net.moddedmite.mitemod.bex.mixin.compat.container;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.EnchantmentHelper;
import net.minecraft.ItemStack;
import net.moddedmite.mitemod.bex.register.BEXEnchantments;
import net.xiaoyu233.mitemod.miteite.inventory.container.ForgingTableSlots;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ForgingTableSlots.class)
public abstract class ForgingTableSlotsMixin {

    @ModifyReturnValue(method = "getChanceOfFailure", at = @At("TAIL"))
    public int getChanceOfFailure(int original, @Local(name = "axeItem") ItemStack axeItem, @Local(name = "hammer") ItemStack hammer) {
        int enchantment = 0;
        if (hammer != null) {
            enchantment += EnchantmentHelper.getEnchantmentLevel(BEXEnchantments.EnchantmentForge, hammer) * 2;
        }
        if (axeItem != null) {
            enchantment += EnchantmentHelper.getEnchantmentLevel(BEXEnchantments.EnchantmentForge, axeItem) * 2;
        }
        return original - enchantment;
    }
}
