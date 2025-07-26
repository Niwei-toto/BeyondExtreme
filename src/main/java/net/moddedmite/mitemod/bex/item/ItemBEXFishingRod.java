package net.moddedmite.mitemod.bex.item;

import moddedmite.rustedironcore.api.item.FishingRodItem;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;
import net.moddedmite.mitemod.bex.BEXInit;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class ItemBEXFishingRod extends FishingRodItem {
    private Icon cast_icon;
    private Icon uncast_icon;
    private final Material material;

    public ItemBEXFishingRod(int id, Material hook_material) {
        super(id, hook_material);
        this.material = hook_material;
        this.setCreativeTab(BEXItems.tabBEX);
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.cast_icon = par1IconRegister.registerIcon(this.getIconString() + "_cast");// vanilla cast icon
        this.uncast_icon = par1IconRegister.registerIcon(BEXInit.BEXResourceId + "fishing_rods/" + this.material.name + "_uncast");
    }

    public Icon getIconFromSubtype(int par1) {
        return this.uncast_icon;
    }

    public Icon func_94597_g() {
        return this.cast_icon;
    }
}
