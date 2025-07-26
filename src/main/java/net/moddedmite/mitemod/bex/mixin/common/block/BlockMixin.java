package net.moddedmite.mitemod.bex.mixin.common.block;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.Block.bedrock;
import static net.minecraft.Block.mantleOrCore;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        bedrock.setHardness(100);
        mantleOrCore.setHardness(100);
    }
}
