package net.moddedmite.mitemod.bex.register;

import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.moddedmite.mitemod.bex.item.enchantment.EnchantmentForge;
import net.moddedmite.mitemod.bex.item.enchantment.EnchantmentRangeAttack;
import net.moddedmite.mitemod.bex.item.enchantment.EnchantmentUnassailable;
import net.xiaoyu233.fml.reload.event.EnchantmentRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;
import net.xiaoyu233.mitemod.miteite.api.ITEEnchantment;
import net.xiaoyu233.mitemod.miteite.item.enchantment.MITEITEEnchantmentRegistryInit;

public class BEXEnchantments {
    public static final Enchantment EnchantmentForge = new EnchantmentForge(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);
    public static final Enchantment enchantmentRangeAttack = new EnchantmentRangeAttack(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 30);
    public static final Enchantment enchantmentUnassailable = new EnchantmentUnassailable(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 30);

    public static void registerEnchantments(EnchantmentRegistryEvent event) {
        event.registerEnchantment(EnchantmentForge, enchantmentRangeAttack, enchantmentUnassailable);
        for (Enchantment enchantment : Enchantment.enchantmentsList) {
            if (enchantment != null && !MITEITEEnchantmentRegistryInit.individualEnchantments.contains(enchantment)){
                if (((ITEEnchantment) enchantment).enchantIndividually()) {
                    MITEITEEnchantmentRegistryInit.individualEnchantments.add(enchantment);
                }
            }
        }
    }
}
