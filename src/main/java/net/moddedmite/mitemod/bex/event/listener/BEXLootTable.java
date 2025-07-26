package net.moddedmite.mitemod.bex.event.listener;

import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.Item;
import net.minecraft.WeightedRandomChestContent;

import java.util.List;

public class BEXLootTable implements ILootTableRegisterListener {
    public void onDungeonUnderworldRegister(List<WeightedRandomChestContent> original) {
        original.add(new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 4, 10));
    }
}
