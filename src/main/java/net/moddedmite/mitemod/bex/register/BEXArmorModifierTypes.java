package net.moddedmite.mitemod.bex.register;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.ArmorModifierTypes;
import net.moddedmite.mitemod.bex.item.ItemInfinityArmor;

import java.util.function.Predicate;

public class BEXArmorModifierTypes extends ArmorModifierTypes {

    public BEXArmorModifierTypes(float levelAddition, String unlocalizedName, EnumChatFormatting color, int weight, int maxLevel, Predicate<ItemStack> canApplyTo) {
        super(levelAddition, unlocalizedName, color, weight, maxLevel, canApplyTo, unlocalizedName);
    }

    public static final ArmorModifierTypes INVINCIBLE = new ArmorModifierTypes(0.05f,"invincible", EnumChatFormatting.DARK_GREEN, 2, 4, itemStack -> itemStack.getItem() instanceof ItemLeggings || itemStack.getItem() instanceof ItemInfinityArmor);
    public static final ArmorModifierTypes AGILITY = new ArmorModifierTypes(0.05f,"agility", EnumChatFormatting.LIGHT_PURPLE, 2, 4,itemStack -> itemStack.getItem() instanceof ItemBoots || itemStack.getItem() instanceof ItemInfinityArmor);
}
