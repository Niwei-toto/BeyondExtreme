package net.moddedmite.mitemod.bex.item;

import net.minecraft.CreativeTabs;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.Material;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class ItemEndBook extends Item {
    public ItemEndBook(int par1) {
        super(par1, Material.wood, "end_book");
        this.setMaxStackSize(1);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        player.displayGUIChestForMinecart(player.getInventoryEnderChest());
        return true;
    }
}