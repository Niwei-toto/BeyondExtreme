package net.moddedmite.mitemod.bex.event.listener;

import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.moddedmite.mitemod.bex.register.BEXBlocks;
import net.moddedmite.mitemod.bex.register.BEXItems;

import java.util.function.Consumer;

public class BEXCraftingRegistry implements Consumer<CraftingRecipeRegisterEvent> {
    @Override
    public void accept(CraftingRecipeRegisterEvent event) {
        BEXItems.registerRecipes(event);
        BEXBlocks.registerRecipes(event);
    }
}
