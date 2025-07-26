package net.moddedmite.mitemod.bex.item;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class ItemHammer extends ItemTool {

    protected ItemHammer(int par1, Material material) {
        super(par1, material);
        this.setCreativeTab(BEXItems.tabBEX);
        this.addMaterialsEffectiveAgainst(new Material[]{Material.stone});
    }

    public float getBaseDamageVsEntity() {
        return 0.0f;
    }

    public float getBaseHarvestEfficiency(Block block) {
        return super.getBaseHarvestEfficiency(block) * 0.75f;
    }

    public float getBaseDecayRateForBreakingBlock(Block block) {
        return 3.0f;
    }

    public float getBaseDecayRateForAttackingEntity(ItemStack itemStack) {
        return 0.0f;
    }

    public String getToolType() {
        return "hammer";
    }

    public int getNumComponentsForDurability() {
        return 2;
    }

    public Class[] getSimilarClasses() {
        return ItemTool.weapon_classes;
    }
}
