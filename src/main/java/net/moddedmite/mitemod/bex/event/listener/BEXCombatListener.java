package net.moddedmite.mitemod.bex.event.listener;

import moddedmite.rustedironcore.api.event.listener.ICombatListener;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.item.ItemInfinityArmor;
import net.moddedmite.mitemod.bex.item.ItemInfinityPickaxe;
import net.moddedmite.mitemod.bex.item.ItemInfinitySword;
import net.moddedmite.mitemod.bex.register.BEXArmorModifierTypes;

public class BEXCombatListener implements ICombatListener {

    @Override
    public float onPlayerReceiveKnockBackModify(EntityPlayer player, Entity attacker, float original) {
        ItemStack[] itemStacks = player.getWornItems();
        for (ItemStack stack : itemStacks) {
            if (stack.getItem() instanceof ItemInfinityArmor) {
                original = 0;
                break;
            }
        }
        return original;
    }

    @Override
    public float onPlayerRawMeleeDamageModify(EntityPlayer player, Entity target, boolean critical, boolean suspended_in_liquid, float original) {
        float healthPercent = player.getHealth() / player.getMaxHealth();
        float invincibleAmp = 1;
        if (healthPercent >= 0.5f) {
            ItemStack leggings = player.getCurrentArmor(2);
            if (leggings != null) {
                float value = BEXArmorModifierTypes.INVINCIBLE.getModifierValue(leggings.getTagCompound());
                if (value != 0) {
                    invincibleAmp = this.getInvincibleAmp(healthPercent, player);
                }
            }
        }
        return original * invincibleAmp;
    }

    private float getInvincibleAmp(float healthPercent, EntityPlayer player) {
        ItemStack leggings = player.getCurrentArmor(2);
        float value = BEXArmorModifierTypes.INVINCIBLE.getModifierValue(leggings.getTagCompound());
        if (healthPercent >= 0.875f) {
            return 1.0f + (value * 4);
        } else if (healthPercent >= 0.75f) {
            return 1.0f + (value * 3);
        } else if (healthPercent >= 0.625f) {
            return 1.0f + (value * 2);
        } else if (healthPercent >= 0.5f) {
            return 1.0f + (value * 1);
        }
        return 1.0f;
    }

    @Override
    public float onPlayerRawStrVsBlockModify(EntityPlayer player, Item tool, Block block, int metadata, float original) {
        if (tool instanceof ItemInfinityPickaxe) {
            return original * Short.MAX_VALUE;
        }
        return original;
    }
}
