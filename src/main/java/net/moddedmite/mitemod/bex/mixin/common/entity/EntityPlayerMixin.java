package net.moddedmite.mitemod.bex.mixin.common.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.item.ItemInfinitySword;
import net.moddedmite.mitemod.bex.register.BEXEnchantments;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.moddedmite.mitemod.bex.register.BEXToolModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase {

   public EntityPlayerMixin(World par1World) {
      super(par1World);
   }

   @Shadow
   public abstract boolean isImmuneByGrace();

   @Shadow protected FoodStats foodStats;

   @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("HEAD"))
   private void applyRangeAttack(Entity target, CallbackInfo ci) {
      if (!this.isImmuneByGrace() && target.canAttackWithItem()) {
         ItemStack heldItemStack = this.getHeldItemStack();
         if (EnchantmentHelper.hasEnchantment(heldItemStack, BEXEnchantments.enchantmentRangeAttack)) {
            List<Entity> targets = this.getNearbyEntities(30, 30);
            if (targets.size() > 0) {
               this.attackMonsters(targets);
            }
         }
      }
   }

   public void attackMonsters(List<Entity> targets) {
      for (Entity target : targets) {
         EntityMob entityMonster = target instanceof EntityMob ? (EntityMob) target : null;
         if (entityMonster != null) {
            entityMonster.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(this.getAsPlayer()), 30));
         }
      }
   }

//   @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;heal(FLnet/minecraft/EnumEntityFX;)V"))
//   private void applyApocalypse(Entity target, CallbackInfo ci) {
//
//   }

   @Inject(method = "onUpdate", at = @At("TAIL"))
   private void addUnassailableEffect(CallbackInfo ci) {
      ItemStack[] wornItems = this.getWornItems();
      for (ItemStack wornItem : wornItems) {
         if (wornItem != null && wornItem.hasEnchantment(BEXEnchantments.enchantmentUnassailable, false)) {
            this.addPotionEffect(new PotionEffect(Potion.resistance.id, 10, 127, true));
         }
      }
   }

   @WrapOperation(method = "attackEntityFrom",
           at = @At(value = "INVOKE",
                   target = "Lnet/minecraft/EntityLivingBase;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
   private EntityDamageResult avoidInfSwordDamage(EntityPlayer instance, Damage damage, Operation<EntityDamageResult> original) {
      ItemStack item_stack = damage.getSource().getItemAttackedWith();
      if (item_stack != null && item_stack.getItem() instanceof ItemInfinitySword && item_stack.itemID == BEXItems.infinitySword.itemID) {
         return null;
      }
      return original.call(instance, damage);
   }
}
