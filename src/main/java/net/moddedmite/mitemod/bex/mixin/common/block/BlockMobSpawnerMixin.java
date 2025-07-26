package net.moddedmite.mitemod.bex.mixin.common.block;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.entity.EntityUltimateAnnihilationSkeleton;
import net.moddedmite.mitemod.bex.entity.EntitySkeletonBoss;
import net.moddedmite.mitemod.bex.util.BEXConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockMobSpawner.class)
public class BlockMobSpawnerMixin extends BlockContainer {

    protected BlockMobSpawnerMixin(int par1, Material par2Material, BlockConstants block_constants) {
        super(par1, par2Material, block_constants);
    }

    @Inject(method = "dropBlockAsEntityItem", at = @At("RETURN"))
    public void summonSkeletonBoss(BlockBreakInfo info, CallbackInfoReturnable<Integer> cir) {
        if (info.world.isUnderworld() && info.world.rand.nextFloat() < BEXConfigs.bexConfig.skeletonBossSpawnPercent.ConfigValue) {
            EntitySkeletonBoss entitySkeletonBoss = new EntitySkeletonBoss(info.world);
            entitySkeletonBoss.setPosition(info.x, info.y, info.z);
            entitySkeletonBoss.refreshDespawnCounter(-9600);
            if (info.getResponsiblePlayer() != null) {
                entitySkeletonBoss.setAttackTarget(info.getResponsiblePlayer());
            }
            entitySkeletonBoss.entityFX(EnumEntityFX.summoned);
            entitySkeletonBoss.onSpawnWithEgg(null);
            info.world.spawnEntityInWorld(entitySkeletonBoss);
        }
        if (info.world.isTheNether()) {
            EntityUltimateAnnihilationSkeleton uas = new EntityUltimateAnnihilationSkeleton(info.world);
            uas.setPosition(info.x, info.y, info.z);
            uas.refreshDespawnCounter(-9600);
            if(info.getResponsiblePlayer() != null) {
                uas.setAttackTarget(info.getResponsiblePlayer());
            }
            uas.entityFX(EnumEntityFX.summoned);
            uas.onSpawnWithEgg(null);
            info.world.spawnEntityInWorld(uas);
        }
    }

    @Shadow
    public TileEntity createNewTileEntity(World world) {
        return null;
    }
}
