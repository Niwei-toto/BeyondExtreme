package net.moddedmite.mitemod.bex.mixin.common.util;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXArmorModifierTypes;
import net.moddedmite.mitemod.bex.register.BEXToolModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Damage.class)
public class DamageMixin {
   @Shadow private float amount;
   @Unique private float block_boost;

   @Inject(method = "applyTargetDefenseModifiers", at = @At(value = "INVOKE", target = "Lnet/minecraft/Damage;bypassesMundaneArmor()Z"), cancellable = true)
   protected void applyAgilityYModifiers(EntityLivingBase target, EntityDamageResult result, CallbackInfoReturnable<Float> cir) {
      block_boost = 0.0F;
      float evasion_chance = 0.0f;
      if (target.getHeldItemStack() != null) {
         block_boost = (BEXToolModifierTypes.LAST_STAND.getModifierValue(target.getHeldItemStack().getTagCompound()));
      }
      if (target.getBoots() != null){
         evasion_chance = BEXArmorModifierTypes.AGILITY.getModifierValue(target.getBoots().getTagCompound());
      }
      if ((double) evasion_chance > Math.random()){
         cir.setReturnValue(0.0F);
      }
   }

   @WrapOperation(method = "applyTargetDefenseModifiers", at = @At(value = "FIELD", target = "Lnet/minecraft/Damage;amount:F", ordinal = 4))
   protected float applyLastStandModifiers_1(Damage instance, Operation<Float> original) {
      this.amount /= 2.0F + block_boost;
      return 1.1F;
   }
}
