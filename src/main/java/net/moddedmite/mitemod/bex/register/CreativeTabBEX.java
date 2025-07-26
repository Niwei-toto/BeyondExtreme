package net.moddedmite.mitemod.bex.register;

import huix.glacier.api.extension.creativetab.GlacierCreativeTabs;

public class CreativeTabBEX extends GlacierCreativeTabs {
    public CreativeTabBEX() {
        super("bex");
    }

    @Override
    public int getTabIconItemIndex() {
        return BEXItems.infinitySword.itemID;
    }
}
