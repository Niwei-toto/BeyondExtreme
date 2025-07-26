package net.moddedmite.mitemod.bex.mixin.common.entity;

import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EntityRevenant.class)
public class EntityRevenantMixin {

   @Inject(method = "addRandomWeapon", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 1))
   private void addIronClub(CallbackInfo ci, @Local(name = "items") List items) {
      items.add(new RandomItemListEntry(EXItemsRegistryInit.clubIron, 2));
   }
}
