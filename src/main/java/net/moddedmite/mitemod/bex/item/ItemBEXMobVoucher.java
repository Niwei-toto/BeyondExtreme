package net.moddedmite.mitemod.bex.item;

import cn.wensc.mitemod.extreme.item.ItemMobVoucher;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;

import java.util.List;

public class ItemBEXMobVoucher extends ItemMobVoucher {
    String entityName = "";

    public ItemBEXMobVoucher(int id, String entityName) {
        super(id, entityName);
        this.entityName = entityName;
        this.setCreativeTab(BEXItems.tabBEX);
    }

    @Override
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add(" ");
            switch (this.entityName) {
                case "voucher_zombieboss":
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted( "攻伐之剑合成材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted( "僵尸Boss掉落", new Object[0]));
                    break;
                case "ghast":
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted("振金锭与金身核心合成材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted("恶魂掉落", new Object[0]));
                    break;
                case "bedrock":
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted("金身核心合成材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted("基岩元素掉落", new Object[0]));
                    break;
                case "vibraniumdoor":
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted("金身核心合成材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted("盗贼僵尸领主掉落", new Object[0]));
                    break;
                case "goldbody_core":
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted("无懈可击之甲合成材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted("血泪凭证，基岩凭证，盗贼僵尸领主凭证合成", new Object[0]));
                    break;
                case "skeletonboss":
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted("金身核心合成材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted("骷髅boss掉落", new Object[0]));
                    break;
                case "ultimateannihilation":
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted("???合成材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted("终湮骷髅掉落", new Object[0]));
                    break;
                case "spiderqueen":
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted("振金锭合成材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted("蛛后掉落", new Object[0]));
                    break;
                default:
                    info.add(EnumChatFormatting.BROWN + Translator.getFormatted("振金核心材料之一", new Object[0]));
                    info.add(EnumChatFormatting.BLUE + Translator.getFormatted("击杀特定怪物掉落", new Object[0]));
                    break;
            }
        }
    }
}
