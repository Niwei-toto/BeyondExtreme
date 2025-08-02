package net.moddedmite.mitemod.bex.mixin.common.world;

import net.minecraft.*;
import net.moddedmite.mitemod.bex.entity.*;
//import net.xiaoyu233.mitemod.miteite.entity.EntityThunderMan;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeGenUnderworld.class)
public class BiomeGenUnderworldMixin extends BiomeGenBase{

   protected BiomeGenUnderworldMixin(int par1) {
      super(par1);
   }

   @Inject(method = "<init>",at = @At("RETURN"))
   public void injectCtor(CallbackInfo callbackInfo) {
      this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieMiner.class, 6, 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderQueen.class, 6, 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieDoorLord.class, 1, 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieDoorDeep.class, 1, 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityBedrockElemental.class, 1, 1, 1));
   }
}
