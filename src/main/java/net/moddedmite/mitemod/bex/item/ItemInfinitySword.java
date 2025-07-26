package net.moddedmite.mitemod.bex.item;

import java.util.List;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.moddedmite.mitemod.bex.register.BEXMaterials;
import net.moddedmite.mitemod.bex.util.ColorText;

public class ItemInfinitySword extends ItemSword {

    public final int Type;

    public ItemInfinitySword(int par1, int type) {
        super(par1, BEXMaterials.infinity);
        Type = type;
        this.addMaterialsEffectiveAgainst(new Material[]{Material.adamantium});
        this.setReachBonus(300.0F);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    @Override
    public Class[] getSimilarClasses() {
        return ItemTool.weapon_classes;
    }

    @Override
    public int getNumComponentsForDurability() {
        return 4;
    }

    @Override
    public float getBaseDamageVsEntity() {
        if (Type == 0) {
            return Integer.MAX_VALUE / 20;
        } else {
            return Float.MAX_VALUE;
        }
    }

    @Override
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (Type == 0) {
            info.add(" ");
            info.add(ColorText.makeColors("极其大的伤害"));
            info.add(" ");
            super.addInformation(item_stack, player, info, extended_info, slot);
        } else {
            info.add(" ");
            info.add(EnumChatFormatting.DARK_RED + "中二之剑,开挂获取");
            info.add(" ");
            super.addInformation(item_stack, player, info, extended_info, slot);
        }
    }
}

