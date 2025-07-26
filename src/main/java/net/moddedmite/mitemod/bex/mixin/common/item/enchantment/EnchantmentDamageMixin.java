package net.moddedmite.mitemod.bex.mixin.common.item.enchantment;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentDamage.class)
public abstract class EnchantmentDamageMixin extends Enchantment {
   protected EnchantmentDamageMixin(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   @Inject(method = "canEnchantItem", at = @At("HEAD"), cancellable = true)
   public void injectVibraniumCheck(Item item, CallbackInfoReturnable<Boolean> cir) {
      if (this == Enchantment.smite && ((item.getHardestMetalMaterial() == BEXMaterials.enchant))) {
         cir.setReturnValue(true);
      }
   }
}
