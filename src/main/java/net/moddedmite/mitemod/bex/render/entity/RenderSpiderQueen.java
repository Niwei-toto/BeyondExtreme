package net.moddedmite.mitemod.bex.render.entity;

import net.minecraft.EntityLivingBase;
import net.minecraft.ModelArachnid;
import net.minecraft.RenderArachnid;
import net.moddedmite.mitemod.bex.BEXInit;
import org.lwjgl.opengl.GL11;

public class RenderSpiderQueen extends RenderArachnid {
    private float scale;

    protected boolean forceGlowOverride(){
        return true;
    }

    public RenderSpiderQueen(float scale) {
        super(new ModelArachnid(), new ModelArachnid(), scale);
        this.scale = scale;
    }

    @Override
    protected void setTextures() {
        this.setTexture(0, BEXInit.BEXResourceId + "textures/entity/spider/" + this.getSubtypeName());
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        super.preRenderCallback(par1EntityLivingBase, par2);
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    public String getSubtypeName() {
        return "spider_queen";
    }

}