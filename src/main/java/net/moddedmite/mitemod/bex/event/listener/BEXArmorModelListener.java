package net.moddedmite.mitemod.bex.event.listener;

import moddedmite.rustedironcore.api.event.listener.IArmorModelListener;
import net.minecraft.ItemArmor;
import net.minecraft.Material;
import net.minecraft.ResourceLocation;
import net.moddedmite.mitemod.bex.BEXInit;
import net.moddedmite.mitemod.bex.register.BEXMaterials;

import java.util.HashMap;
import java.util.Map;

public class BEXArmorModelListener implements IArmorModelListener {
    private final Map<String, ResourceLocation> BEX_TEXTURE_MAP = new HashMap<>();

    @Override
    public ResourceLocation getArmorTexture(ItemArmor itemArmor, int slotIndex) {
        Material material = itemArmor.getArmorMaterial();
        if (!(BEXMaterials.infinity == material)) {
            return null;
        }

        String path = String.format("textures/models/armor/%s_layer_%d.png", itemArmor.getTextureFilenamePrefix(), slotIndex == 2 ? 2 : 1);
        ResourceLocation identifier = this.BEX_TEXTURE_MAP.get(path);
        if (identifier == null) {
            identifier = new ResourceLocation(BEXInit.BEXModId, path);
            this.BEX_TEXTURE_MAP.put(path, identifier);
        }
        return identifier;
    }
}
