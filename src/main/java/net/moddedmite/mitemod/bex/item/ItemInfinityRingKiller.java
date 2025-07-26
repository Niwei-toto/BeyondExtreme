package net.moddedmite.mitemod.bex.item;

import cn.wensc.mitemod.extreme.item.ItemRingKiller;
import net.minecraft.Material;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class ItemInfinityRingKiller extends ItemRingKiller {
    public ItemInfinityRingKiller(int par1, Material material) {
        super(par1, material);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    @Override
    public int getRingKillerSkillCoolDownTime() {
        return 0;
    }

    @Override
    public float getRingKillerSkillRange() {
        return 5.0F;
    }

    @Override
    public float getRingKillerSkillDamage() {
        return Short.MAX_VALUE;
    }
}
