package net.moddedmite.mitemod.bex.mixin.common.entity;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDragon.class)
public class EntityEnderDragonMixin extends EntityLiving {
   public EntityEnderDragonMixin(World par1World) {
      super(par1World);
   }

   @Inject(method = "applyEntityAttributes", at = @At("TAIL"))
   protected void moreMaxHP(CallbackInfo ci) {
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(2500D);
   }
}
