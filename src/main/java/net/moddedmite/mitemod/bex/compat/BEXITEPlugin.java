package net.moddedmite.mitemod.bex.compat;

import net.moddedmite.mitemod.bex.item.ItemInfinitySword;
import net.moddedmite.mitemod.bex.register.BEXMaterials;
import net.xiaoyu233.mitemod.miteite.api.ITEPlugin;
import net.xiaoyu233.mitemod.miteite.api.ITERegistry;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;

public class BEXITEPlugin implements ITEPlugin {
    @Override
    public void register(ITERegistry registry) {
        registry.registerWeaponCriteria(item -> item instanceof ItemInfinitySword);
        registry.registerForgingRecipe(BEXMaterials.enchant, ForgingTableLevel.VIBRANIUM, 8);
        registry.registerForgingRecipe(BEXMaterials.infinity, ForgingTableLevel.VIBRANIUM, 9);
        registry.registerExpForLevel(BEXMaterials.enchant, 256, 96);
        registry.registerExpForLevel(BEXMaterials.infinity, 1, 1);
    }
}
