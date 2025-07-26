package net.moddedmite.mitemod.bex.mixin.common.item.enchantment;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EnchantmentVampiric.class, priority = 3000)
public class EnchantmentVampiricMixin {
    @Inject(method = "canEnchantItem", at = @At(value = "HEAD"), cancellable = true)
    private void enchantMaterialCanEnchant(Item item, CallbackInfoReturnable<Boolean> cir) {
        if (item instanceof ItemTool) {
            Material material = ((ItemTool) item).getToolMaterial();
            if (material == BEXMaterials.enchant) cir.setReturnValue(true);
        }
    }
}
