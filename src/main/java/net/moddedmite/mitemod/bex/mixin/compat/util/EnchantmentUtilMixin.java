package net.moddedmite.mitemod.bex.mixin.compat.util;

import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.moddedmite.mitemod.bex.register.BEXMaterials;
import net.xiaoyu233.mitemod.miteite.util.EnchantmentUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnchantmentUtil.class)
public class EnchantmentUtilMixin {
    @ModifyVariable(
            method = "mapEnchantmentData",
            at = @At("STORE"),
            ordinal = 0
    )
    private static boolean modifyIsVibranium(boolean original, int enchantment_levels, ItemStack item_stack) {
        Item item = item_stack.getItem();
        if (item.getHardestMetalMaterial() == BEXMaterials.enchant) {
            return true;
        }
        return original;
    }
}
