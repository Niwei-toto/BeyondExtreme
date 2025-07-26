package net.moddedmite.mitemod.bex.register;

import net.minecraft.EnumChatFormatting;
import net.minecraft.ItemPickaxe;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.item.ItemModifierTypes;
import net.xiaoyu233.mitemod.miteite.item.ToolModifierTypes;

import java.util.function.Predicate;

public class BEXToolModifierTypes extends ToolModifierTypes {

    public BEXToolModifierTypes(float levelAddition, String unlocalizedName, EnumChatFormatting color, int weight, Predicate<ItemStack> canApplyTo, int maxLevel) {
        super(levelAddition, unlocalizedName, color, weight, canApplyTo, maxLevel, unlocalizedName);
    }

    public static final ToolModifierTypes MELTING = new ToolModifierTypes(1.0f, "melting", EnumChatFormatting.RED,1, itemStack -> itemStack.getItem() instanceof ItemPickaxe && hasNotOtherMiningModifier(itemStack, 1), 1);
    public static final ToolModifierTypes LAST_STAND = new ToolModifierTypes(0.67F, "last_stand", EnumChatFormatting.YELLOW, 1, (stack -> true), 3);
    public static final ToolModifierTypes NATURE_BLESSING = new ToolModifierTypes(0.125f, "nature_blessing", EnumChatFormatting.GREEN, 1, ToolModifierTypes::isWeapon, 4);
    public static final ToolModifierTypes APOCALYPSE = new ToolModifierTypes(1.0f, "apocalypse", EnumChatFormatting.DARK_RED, 2, ToolModifierTypes::isWeapon, 4);

    private static boolean hasNotOtherMiningModifier(ItemStack stack, int MiningType) {
        return switch (MiningType) {
            case 0 -> !ItemModifierTypes.hasModifier(stack, MELTING);
            case 1 -> !ItemModifierTypes.hasModifier(stack, GEOLOGY);
            default -> true;
        };
    }
}
