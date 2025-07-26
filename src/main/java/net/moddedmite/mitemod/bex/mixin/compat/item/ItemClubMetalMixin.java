package net.moddedmite.mitemod.bex.mixin.compat.item;

import cn.wensc.mitemod.extreme.item.ItemClubMetal;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemClubMetal.class)
public class ItemClubMetalMixin {
    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lcn/wensc/mitemod/extreme/item/ItemClubMetal;addMaterialsEffectiveAgainst([Lnet/minecraft/Material;)V"))
    private void removedMaterialsEffective(ItemClubMetal instance, Material[] materials, Operation<Void> original) {

    }
}
