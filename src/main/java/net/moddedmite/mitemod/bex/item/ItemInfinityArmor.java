package net.moddedmite.mitemod.bex.item;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.moddedmite.mitemod.bex.util.ColorText;

import java.util.List;

public class ItemInfinityArmor extends ItemArmor {

    public ItemInfinityArmor(int par1, Material material,int par4, boolean is_chain_mail) {
        super(par1, material,par4, is_chain_mail);
        this.setMaxStackSize(127);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    @Override
    public String getArmorType() {
        return null;
    }

    @Override
    public int getNumComponentsForDurability() {
        return 100000;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        info.add(" ");
        info.add(ColorText.makeColors("极高的防御"));
        info.add(" ");
        super.addInformation(itemStack, player, info, extended_info, slot);
    }
}
