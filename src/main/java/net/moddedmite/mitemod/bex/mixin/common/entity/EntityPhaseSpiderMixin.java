package net.moddedmite.mitemod.bex.mixin.common.entity;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityPhaseSpider.class})
public abstract class EntityPhaseSpiderMixin extends EntityWoodSpider {

    public EntityPhaseSpiderMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void piercingCountermeasures(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        ItemStack item_stack;
        DamageSource damage_source = damage.getSource();
        if (damage_source != null && (item_stack = damage_source.getItemAttackedWith()) != null && item_stack.hasEnchantment(Enchantment.piercing, true)) {
            cir.setReturnValue(super.attackEntityFrom(damage));
        }
    }
}