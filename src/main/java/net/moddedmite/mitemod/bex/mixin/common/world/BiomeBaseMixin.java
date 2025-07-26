package net.moddedmite.mitemod.bex.mixin.common.world;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.entity.EntitySpiderQueen;
import net.moddedmite.mitemod.bex.entity.EntityZombieMiner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BiomeGenBase.class)
public class BiomeBaseMixin {
   @Shadow protected List<SpawnListEntry> spawnableMonsterList;

   @Inject(method = "<init>",at = @At("RETURN"))
   private void injectInit(CallbackInfo callbackInfo){
      this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieMiner.class, 6, 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderQueen.class, 4, 1, 1));
//      this.spawnableMonsterList.add(new BiomeMeta(EntityZombieDoorLord.class, 1, 1, 1));
   }
}
