package net.moddedmite.mitemod.bex.mixin.common.block;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXToolModifierTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BlockOre.class)
public class BlockOreMixin {
    @ModifyArg(method = "dropBlockAsEntityItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/Block;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;IIIF)I"), index = 1)
    private int applyMeltingModifier(int id_dropped, @Local(argsOnly = true) BlockBreakInfo info, @Local boolean suppress_fortune) {
        EntityPlayer player = info.getResponsiblePlayer();
        float melting = 0.0F;
        int meltingID = 0;
        if (player.getHeldItemStack() != null) {
            melting = (BEXToolModifierTypes.MELTING.getModifierValue(player.getHeldItemStack().getTagCompound()));
        }
        if (melting != 0.0F) {
            if (id_dropped == Block.oreCopper.blockID) {
                meltingID = Item.ingotCopper.itemID;
            } else if (id_dropped == Block.oreSilver.blockID) {
                meltingID = Item.ingotSilver.itemID;
            } else if (id_dropped == Block.oreGold.blockID) {
                meltingID = Item.ingotGold.itemID;
            } else if (id_dropped == Block.oreIron.blockID) {
                meltingID = Item.ingotIron.itemID;
            } else if (id_dropped == Block.oreMithril.blockID) {
                meltingID = Item.ingotMithril.itemID;
            }
            if (meltingID != 0) return meltingID;
        }
        return id_dropped;
    }
}
