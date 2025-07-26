package net.moddedmite.mitemod.bex.event.listener;

import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import moddedmite.rustedironcore.api.event.events.TradingRegisterEvent;
import net.minecraft.ItemStack;
import net.minecraft.MerchantRecipe;

import java.util.function.Consumer;

public class BEXTrading implements Consumer<TradingRegisterEvent> {
    @Override
    public void accept(TradingRegisterEvent event) {
        event.getForProfession(2).ifPresent(x -> x.addEntry((recipeList, villager, rand) -> {
                    if (rand.nextFloat() < villager.adjustProbability(0.1F)) {
                        recipeList.add(new MerchantRecipe(new ItemStack(EXItemsRegistryInit.fancyRed, 16), new ItemStack(EXItemsRegistryInit.voucherVillager, 1)));
                    }
                }
        ));
    }
}
