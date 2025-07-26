package net.moddedmite.mitemod.bex.item;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.moddedmite.mitemod.bex.util.ColorText;

import java.util.List;

public class ItemFinalFood extends ItemFood {
    public ItemFinalFood(int par1) {
        super(par1, Material.chicken_soup, 200, 200, true, true, true, "wan");
        this.setMaxStackSize(64);
        this.setCraftingDifficultyAsComponent(1.0f);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    public boolean isHarmedByPepsin() {
        return false;
    }

    public boolean isHarmedByAcid() {
        return false;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 10;
    }

    protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 36000, 1, false));
//        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, 6000, 5, false));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 36000, 2, false));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 36000, 127, false));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 36000, 5, false));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 36000, 1, false));
            par3EntityPlayer.setInsulinResistance(par3EntityPlayer.getInsulinResistance() - 12800);
        } else {
            super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        }
    }

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add(" ");
            info.add(ColorText.makeFabulous("+200饱和度"));
            info.add(ColorText.makeFabulous("+200饱食度"));
            info.add(EnumChatFormatting.YELLOW + ("+160000蛋白质"));
            info.add(EnumChatFormatting.GREEN + ("+160000植物营养"));
            info.add(ColorText.makeColors("-12800血糖"));
            info.add(ColorText.makeColors("+30分钟饱和，抗性提升127，生命恢复2，抗火1, 速度2"));
            info.add("");
            info.add(ColorText.makeSANIC("贪婪使我强大"));
        }
    }
}
