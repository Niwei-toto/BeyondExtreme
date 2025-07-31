package net.moddedmite.mitemod.bex.block;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.xiaoyu233.mitemod.miteite.entity.EntityAncientDragon;

public class BlockDragonSpawn extends Block {
    public BlockDragonSpawn(int par1) {
        super(par1, Material.wood, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
//        this.setBounds(true);
        this.setMaxStackSize(64);
        this.setCreativeTab(BEXItems.tabBEX);
    }

    boolean HasSpawnDragon = false;


//    private void setBounds(boolean for_all_threads) {
//        float var1 = 0.375F;
//        float var2 = var1 / 2.0F;
//        // 前 下 厚度 高度 宽度
//        this.setBlockBounds((double)(0.5F - var2), 0.0D, (double)(0.5F - var2), (double)(0.5F + var2), (double)0.6875D, (double)(0.5F + var2),
//                for_all_threads);
//    }
//    public void setBlockBoundsForItemRender(int item_damage) {
//        this.setBounds(false);
//    }
//    Random random = new Random();
//    int id = random.nextInt(3500);
//    public int dropBlockAsEntityItem(BlockBreakInfo info) {
//        return id == Blocks.getNextBlockID();
//    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, 15 + info.world.rand.nextInt(15) + info.world.rand.nextInt(15));
        if (info.world.isUnderworld() && info.world.rand.nextFloat() < 100) {
            EntityAncientDragon ancientDragon = new EntityAncientDragon(info.world);
            ancientDragon.setPosition(info.x, info.y, info.z);
            ancientDragon.refreshDespawnCounter(-9600);
            if (info.getResponsiblePlayer() != null) {
                ancientDragon.setAttackTarget(info.getResponsiblePlayer());
            }
            ancientDragon.entityFX(EnumEntityFX.summoned);
            ancientDragon.onSpawnWithEgg(null);
            info.world.spawnEntityInWorld(ancientDragon);
            HasSpawnDragon = true;
        }
        if (info.world.isTheEnd() && info.world.rand.nextFloat() < 100) {
            EntityDragon endDragon = new EntityDragon(info.world);
            endDragon.setPosition(info.x, info.y, info.z);
            endDragon.refreshDespawnCounter(-9600);
            if (info.getResponsiblePlayer() != null) {
                endDragon.setAttackTarget(info.getResponsiblePlayer());
            }
            endDragon.entityFX(EnumEntityFX.summoned);
            endDragon.onSpawnWithEgg(null);
            info.world.spawnEntityInWorld(endDragon);
            HasSpawnDragon = true;
        }

        return 0;
    }
}
