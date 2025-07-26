package net.moddedmite.mitemod.bex.mixin.compat.entity;

import cn.wensc.mitemod.extreme.entity.EntityZombieBoss;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityZombieBoss.class)
public class EntityZombieBossMixin extends EntityZombie {

    public EntityZombieBossMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "dropFewItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/InventoryPlayer;addItemStackToInventoryOrDropIt(Lnet/minecraft/ItemStack;)V"))
    private void addDropVoucher(boolean recently_hit_by_player, DamageSource damage_source, CallbackInfo ci, @Local(name = "player") EntityPlayer player) {
        player.inventory.addItemStackToInventoryOrDropIt(new ItemStack(BEXItems.voucherZombieBoss, 1));
    }
}
