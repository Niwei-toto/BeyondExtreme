package net.moddedmite.mitemod.bex.event.listener;

import moddedmite.rustedironcore.api.event.listener.IAchievementListener;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.moddedmite.mitemod.bex.register.BEXAchievements;
import net.moddedmite.mitemod.bex.register.BEXItems;

public class BEXAchievementListener implements IAchievementListener {
    @Override
    public void onItemPickUp(EntityPlayer player, ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (item == BEXItems.infinitySword) {
            player.triggerAchievement(BEXAchievements.infinitySword);
        } else if (item == BEXItems.voucherUltimateAnnihilation) {
            player.triggerAchievement(BEXAchievements.UAKiller);
        }
    }
}
