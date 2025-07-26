package net.moddedmite.mitemod.bex.mixin.compat.entity;

import cn.wensc.mitemod.extreme.entity.EntityMirrorSkeleton;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.EntityLivingBase;
import net.minecraft.ItemStack;
import net.minecraft.ItemTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityMirrorSkeleton.class)
public class EntityMirrorSkeletonMixin {

    @WrapOperation(method = "onUpdate", at = @At(value = "INVOKE", target = "Lcn/wensc/mitemod/extreme/entity/EntityMirrorSkeleton;setCurrentItemOrArmor(ILnet/minecraft/ItemStack;)V"))
    private void onlyAllowTool(EntityMirrorSkeleton instance, int i, ItemStack itemStack, Operation<Void> original, @Local(name = "target") EntityLivingBase target) {
        if (itemStack.getItem() instanceof ItemTool) {
            instance.setCurrentItemOrArmor(i, itemStack);
        }
    }
}
