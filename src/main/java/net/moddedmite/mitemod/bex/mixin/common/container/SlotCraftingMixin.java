package net.moddedmite.mitemod.bex.mixin.common.container;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(value = SlotCrafting.class, priority = 3000)
public class SlotCraftingMixin extends Slot {

   public SlotCraftingMixin(IInventory inventory, int slot_index, int display_x, int display_y) {
      super(inventory, slot_index, display_x, display_y);
   }

   @ModifyExpressionValue(method = "updatePlayerCrafting", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;getCraftingPeriod(F)I"))
   private int makeFast(int original) {
      Container container = this.getContainer();
      if (container instanceof ContainerWorkbench workbench) {
         if (workbench.world.getBlock(workbench.x, workbench.y, workbench.z) == BEXBlocks.infinityWorkBench) {
            return Math.min(original, 25);
         }
      }
      return original;
   }
}
