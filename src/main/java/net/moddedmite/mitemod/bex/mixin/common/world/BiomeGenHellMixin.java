package net.moddedmite.mitemod.bex.mixin.common.world;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import cn.wensc.mitemod.extreme.entity.EntityMirrorSkeleton;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.entity.EntityBedrockElemental;
import net.moddedmite.mitemod.bex.entity.EntitySpiderQueen;
import net.xiaoyu233.mitemod.miteite.entity.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeGenHell.class)
public class BiomeGenHellMixin extends BiomeGenBase {

   protected BiomeGenHellMixin(int par1) {
      super(par1);
   }

   @Inject(method = "<init>",at = @At("RETURN"))
   public void injectCtor(CallbackInfo callbackInfo) {
      this.spawnableMonsterList.add(new SpawnListEntry(EntityBedrockElemental.class, 1, 1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityExchanger.class, 5,1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityMirrorSkeleton.class, 5,1, 1));
      this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderQueen.class, 4, 1, 1));
   }
}
