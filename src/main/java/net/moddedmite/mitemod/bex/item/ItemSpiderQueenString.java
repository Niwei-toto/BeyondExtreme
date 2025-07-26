package net.moddedmite.mitemod.bex.item;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;

import java.util.List;

public class ItemSpiderQueenString extends Item {
    public ItemSpiderQueenString(int par1) {
        super(par1, Material.lava.setDissolvesInWater(), "spider_queen_silk");
        this.setMaxStackSize(16);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add(" ");
            info.add(EnumChatFormatting.RED + Translator.getFormatted("用于合成振金钓鱼竿与振金弓", new Object[0]));
        }
    }
}
