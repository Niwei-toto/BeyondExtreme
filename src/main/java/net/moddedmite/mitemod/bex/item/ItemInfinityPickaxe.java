package net.moddedmite.mitemod.bex.item;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.moddedmite.mitemod.bex.register.BEXMaterials;
import net.moddedmite.mitemod.bex.util.ColorText;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;

import java.util.List;

public class ItemInfinityPickaxe extends ItemPickaxe {
    public ItemInfinityPickaxe(int par1) {
        super(par1, BEXMaterials.infinity);
        this.addMaterialsEffectiveAgainst(new Material[]{
                Material.wood, Material.cloth, Material.pumpkin, Material.dirt, Material.web, Material.sand, Material.mithril,
                Material.adamantium, Materials.vibranium, Material.tree_leaves, Material.grass, Material.glass, Material.dragonEgg,
                Material.snow, Material.web});
        this.setReachBonus(300.0F);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    @Override
    public int getNumComponentsForDurability() {
        return 4;
    }

    @Override
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        info.add(" ");
        info.add(ColorText.makeColors("破坏神"));
        info.add(" ");
        super.addInformation(item_stack, player, info, extended_info, slot);
    }
}
