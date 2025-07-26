package net.moddedmite.mitemod.bex.mixin.common.container;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ContainerEnchantment;
import net.minecraft.Item;
import net.moddedmite.mitemod.bex.register.BEXMaterials;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ContainerEnchantment.class, priority = 2000)
public class ContainerEnchantmentMixin {
    @ModifyConstant(method = "calcEnchantmentLevelsForSlot", constant = @Constant(intValue = 24, ordinal = 0))
    private int enchantMaterialMaxLevel(int constant, @Local Item item) {
        if (item.getHardestMetalMaterial() == BEXMaterials.enchant || item.getHardestMetalMaterial() == Materials.vibranium) {
            return Integer.MAX_VALUE;
        }
        return constant;
    }
}
