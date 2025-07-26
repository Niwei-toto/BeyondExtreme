package net.moddedmite.mitemod.bex.mixin.common.entity;

import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntitySkeleton.class)
public abstract class EntitySkeletonMixin extends EntityMob {

   @Shadow public abstract int getSkeletonType();

   public EntitySkeletonMixin(World par1World) {
      super(par1World);
   }

   @Inject(method = "addRandomWeapon", at = @At("HEAD"))
   private void addAMClub(CallbackInfo ci) {
      if (this.getSkeletonType() == 1 && this.rand.nextInt(7) == 0) {
         this.setCurrentItemOrArmor(0, new ItemStack(EXItemsRegistryInit.clubCopper));
      }
   }

   @Inject(method = "onSpawnWithEgg", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityAITasks;addTask(ILnet/minecraft/EntityAIBase;)V", ordinal = 2))
   private void aVoidPlayer(EntityLivingData par1EntityLivingData, CallbackInfoReturnable<EntityLivingData> cir) {
      if (this.rand.nextInt(7) == 0) {
         this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 12.0F, 1.1, 1.4));
      }
   }
}
