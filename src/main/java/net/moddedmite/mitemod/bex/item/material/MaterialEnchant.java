package net.moddedmite.mitemod.bex.item.material;

import huix.glacier.api.extension.material.IRepairableMaterial;
import huix.glacier.api.extension.material.IToolMaterial;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Item;
import net.minecraft.Material;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class MaterialEnchant extends Material implements IRepairableMaterial, IToolMaterial {

    public MaterialEnchant(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
        this.setMetal(false).setHarmedByLava(false).setMinHarvestLevel(6);
    }

    @Override
    public float getDamageVsEntity() {
        return 8.0F;
    }

    @Override
    public Item getRepairItem() {
        return BEXItems.enchantNugget;
    }

    @Override
    public float getHarvestEfficiency() {
        return 3.0F;
    }
}
