package net.moddedmite.mitemod.bex.mixin.compat.item.material;

import net.minecraft.ItemCoin;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.xiaoyu233.mitemod.miteite.item.material.MaterialVibranium;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MaterialVibranium.class)
public class MaterialVibraniumMixin {
    @Overwrite
    public ItemCoin getForInstance() {
        return BEXItems.coinVibranium;
    }
}
