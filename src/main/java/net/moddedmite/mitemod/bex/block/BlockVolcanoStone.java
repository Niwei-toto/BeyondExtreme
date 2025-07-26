package net.moddedmite.mitemod.bex.block;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXBlocks;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class BlockVolcanoStone extends Block {
    public BlockVolcanoStone(int par1) {
        super(par1, Material.stone, new BlockConstants());
        this.setCreativeTab(BEXItems.tabBEX);
        this.setHardness(3.0F);
        this.setResistance(10.0F);
    }
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return this.dropBlockAsEntityItem(info, BEXBlocks.volcanoCobblestone);
    }
}
