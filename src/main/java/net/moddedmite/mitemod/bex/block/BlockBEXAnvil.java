package net.moddedmite.mitemod.bex.block;

import net.minecraft.BlockAnvil;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;
import net.moddedmite.mitemod.bex.BEXInit;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class BlockBEXAnvil extends BlockAnvil {
    public BlockBEXAnvil(int par1, Material metal_type) {
        super(par1, metal_type);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(BEXInit.BEXResourceId + "anvil/" + this.metal_type.name + "/base");
        super.iconArray = new Icon[anvilIconNames.length];

        for (int var2 = 0; var2 < super.iconArray.length; ++var2) {
            super.iconArray[var2] = par1IconRegister.registerIcon(BEXInit.BEXResourceId + "anvil/" + this.metal_type.name + "/" + anvilIconNames[var2]);
        }

    }
}
