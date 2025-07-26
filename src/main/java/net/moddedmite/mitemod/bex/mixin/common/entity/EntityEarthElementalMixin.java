package net.moddedmite.mitemod.bex.mixin.common.entity;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.item.ItemInfinitySword;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityEarthElemental.class)
public abstract class EntityEarthElementalMixin {

   @Inject(method = "isImmuneTo", at = @At("HEAD"), cancellable = true)
   private void cannotImmuneInfinitySword(DamageSource damage_source, CallbackInfoReturnable<Boolean> cir) {
      ItemStack item_stack = damage_source.getItemAttackedWith();
      if (item_stack != null && (item_stack.getItem() instanceof ItemInfinitySword)) cir.setReturnValue(false);
   }
}
