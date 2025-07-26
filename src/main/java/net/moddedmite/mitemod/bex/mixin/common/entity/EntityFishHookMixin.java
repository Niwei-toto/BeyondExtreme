package net.moddedmite.mitemod.bex.mixin.common.entity;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.util.BEXConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityFishHook.class)
public abstract class EntityFishHookMixin extends Entity {

   public EntityFishHookMixin(World par1World) {
      super(par1World);
   }

   @ModifyConstant(method = "checkForBite", constant = @Constant(intValue = 600))
   private int modifyBlnFish(int constant) {
      return BEXConfigs.bexConfig.BlnFinsh.ConfigValue ? 60 : constant;
   }
   @ModifyConstant(method = "checkForBite", constant = @Constant(floatValue = 600.0F, ordinal = 1))
   private float modifyBlnFish_1(float constant) {
      return BEXConfigs.bexConfig.BlnFinsh.ConfigValue ? 120.0F : constant;
   }
   @ModifyConstant(method = "checkForBite", constant = @Constant(intValue = 2400))
   private int modifyBlnFish_2(int constant) {
      return BEXConfigs.bexConfig.BlnFinsh.ConfigValue ? 240 : constant;
   }
}