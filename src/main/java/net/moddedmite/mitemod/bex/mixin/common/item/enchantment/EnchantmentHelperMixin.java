package net.moddedmite.mitemod.bex.mixin.common.item.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXMaterials;
import net.xiaoyu233.mitemod.miteite.api.ITEEnchantment;
import net.xiaoyu233.mitemod.miteite.util.EnchantmentUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.*;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

   @WrapOperation(method = "mapEnchantmentData", at = @At(value = "INVOKE", target = "Lnet/minecraft/Enchantment;getNumLevels()I"))
   private static int enchantMaterialBetterLevel(Enchantment instance, Operation<Integer> original, @Local(name = "item") Item item) {
      if (item.getHardestMetalMaterial() == BEXMaterials.enchant) {
         return ((ITEEnchantment) instance).getNumLevelsForVibranium();
      }
      return original.call(instance);
   }

   @WrapOperation(method = "buildEnchantmentList", at = @At(value = "INVOKE", target = "Lnet/minecraft/EnchantmentHelper;removeEnchantmentsFromMapThatConflict(Ljava/util/Map;Ljava/util/ArrayList;)V"))
   private static void enchantDamageEnchantmentCompatible(Map id, ArrayList enchantment_data, Operation<Void> original, @Local(argsOnly = true) ItemStack item_stack) {
      if (item_stack.getItem().getHardestMetalMaterial() == BEXMaterials.enchant) {
         EnchantmentUtil.removeEnchantmentsFromMapThatConflict(id, enchantment_data, item_stack.getItem());
      } else {
         original.call(id, enchantment_data);
      }
   }
}
