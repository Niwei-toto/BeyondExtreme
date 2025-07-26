package net.moddedmite.mitemod.bex.mixin.common.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXToolModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLiving.class)
public class EntityLivingMixin {
    @Inject(
            method = {"onMeleeAttacked(Lnet/minecraft/EntityLivingBase;Lnet/minecraft/EntityDamageResult;)V"},
            at = {@At("HEAD")}
    )
    private void injectMeleeAttacked(EntityLivingBase attacker, EntityDamageResult result, CallbackInfo c) {
        if (result != null && attacker != null && result.entityLostHealth() && attacker instanceof EntityPlayer player) {
            ItemStack stack = attacker.getHeldItemStack();
            if (stack != null) {
                int apocalypse;
                ItemStack heldItemStack = player.getHeldItemStack();
                if (heldItemStack != null && (BEXToolModifierTypes.APOCALYPSE.getModifierValue(heldItemStack.getTagCompound()) > 0)) {
                    apocalypse = (int) (BEXToolModifierTypes.APOCALYPSE.getModifierValue(heldItemStack.getTagCompound()));
                    ((EntityLivingBase) result.entity).addPotionEffect(new PotionEffect(Potion.wither.id, apocalypse * 200, apocalypse));
                }
                float bless_of_nature = 0;
                if (heldItemStack != null) {
                    bless_of_nature = BEXToolModifierTypes.NATURE_BLESSING.getModifierValue(heldItemStack.getTagCompound());
                }
                if ((double) bless_of_nature > Math.random() * 2.0D) {
                    player.heal(1);
                }
                if ((double) bless_of_nature > Math.random()) {
                    player.getFoodStats().addNutrition(1);
                    player.getFoodStats().addSatiation(1);
                }
            }
        }
    }

    @WrapOperation(method = "getAttackTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void disableError(String text, Operation<Void> original) {

    }
}
