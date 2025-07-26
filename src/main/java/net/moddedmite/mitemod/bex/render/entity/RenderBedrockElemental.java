package net.moddedmite.mitemod.bex.render.entity;

import net.minecraft.RenderEarthElemental;
import net.moddedmite.mitemod.bex.BEXInit;

public class RenderBedrockElemental extends RenderEarthElemental {

    @Override
    protected void setTextures() {
        this.setTexture(0, BEXInit.BEXResourceId + "textures/entity/earth_elemental/bedrock/earth_elemental_bedrock");
    }
}
