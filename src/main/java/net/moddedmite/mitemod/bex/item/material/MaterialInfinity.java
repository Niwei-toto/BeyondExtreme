package net.moddedmite.mitemod.bex.item.material;

import huix.glacier.api.extension.material.*;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.Material;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class MaterialInfinity extends Material implements IArmorMaterial, IRepairableMaterial, IToolMaterial {

    public MaterialInfinity(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
        this.setMetal(false).setHarmedByLava(false).setMinHarvestLevel(32767);
    }

    @Override
    public float getDamageVsEntity() {
        return 32.0F;
    }

    @Override
    public int getProtection() {
        return 100000000;
    }

    @Override
    public Item getRepairItem() {
        return MITEITEItemRegistryInit.VIBRANIUM_NUGGET;
    }

    @Override
    public float getHarvestEfficiency() {
        return 256.0F;
    }
}
