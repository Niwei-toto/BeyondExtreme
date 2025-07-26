package net.moddedmite.mitemod.bex.mixin.common.item;

import net.minecraft.Block;
import net.minecraft.ItemDoor;
import net.minecraft.Material;
import net.moddedmite.mitemod.bex.register.BEXBlocks;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemDoor.class)
public class ItemDoorMixin {
    @Shadow private Material door_material;

    @Inject(method = "getBlock", at = @At("HEAD"), cancellable = true)
    public void getBlock(CallbackInfoReturnable<Block> cir) {
        if (this.door_material == Materials.vibranium) {
            cir.setReturnValue(BEXBlocks.vibraniumDoor);
        }
    }
}
