package net.moddedmite.mitemod.bex.mixin.common.block.tileentity;

import net.minecraft.TileEntityFurnace;
import net.moddedmite.mitemod.bex.register.BEXItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityFurnace.class)
public class TileEntityFurnaceMixin {
    @Inject(method = "getHeatLevelRequired", at = @At("HEAD"), cancellable = true)
    private static void getEXHeatLevelRequired(int item_id, CallbackInfoReturnable<Integer> cir) {
        if (item_id == BEXItems.enchantSRC.itemID) {
            cir.setReturnValue(5);
        }
    }
}
