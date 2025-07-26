package net.moddedmite.mitemod.bex.mixin.compat.entity;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import cn.wensc.mitemod.extreme.util.Constant;
import cn.wensc.mitemod.extreme.util.EXConfigs;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.entity.EntityZombiePigmanLord;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityZombiePigmanLord.class)
public class EntityZombiePigmanLordMixin extends EntityZombie {

    @Shadow private int fx_counter;
    @Unique private int spawnCounter;
    @Unique private int spawnSums;
    @Unique private boolean haveTryToSpawnExchanger = false;

    public EntityZombiePigmanLordMixin(World par1World) {
        super(par1World);
    }

    @WrapOperation(method = "addRandomEquipment", at = @At(value = "INVOKE", target = "Lnet/xiaoyu233/mitemod/miteite/entity/EntityZombiePigmanLord;setCurrentItemOrArmor(ILnet/minecraft/ItemStack;)V", ordinal = 0))
    protected void addRandomEquipment(EntityZombiePigmanLord instance, int i, ItemStack itemStack, Operation<Void> original, @Local(name = "day") int day) {
        Item[] itemList = new Item[]{MITEITEItemRegistryInit.VIBRANIUM_SWORD, MITEITEItemRegistryInit.VIBRANIUM_BATTLE_AXE, MITEITEItemRegistryInit.VIBRANIUM_WAR_HAMMER};
        this.setCurrentItemOrArmor(0, (new ItemStack(itemList[Constant.EXRandom.nextInt(itemList.length - 1)], 1)).randomizeForMob(this, true));
    }

    @Inject(method = "dropFewItems", at = @At("HEAD"))
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source, CallbackInfo ci) {
        if (recently_hit_by_player) {
            this.dropItem(EXItemsRegistryInit.voucherPigman);
        }
    }

    @Overwrite
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
            EntityLivingBase target;
            if (this.fx_counter > 0) {
                --this.fx_counter;
            } else {
                this.fx_counter = 60;
                this.entityFX(EnumEntityFX.summoned);
            }
            if ((EXConfigs.wenscConfig.isSpawnExchanger.ConfigValue).booleanValue() && (target = this.getAttackTarget()) instanceof EntityPlayer) {
                if (this.spawnSums < 4) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntitySkeleton skeleton = new EntitySkeleton(this.worldObj);
                        if (skeleton.entityId == 207) {
                            return;
                        }
                        skeleton.setPosition(this.posX, this.posY, this.posZ);
                        skeleton.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld((Entity) skeleton);
                        skeleton.onSpawnWithEgg(null);
                        skeleton.addRandomWeapon();
                        skeleton.setAttackTarget(this.getTarget());
                        skeleton.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
                if (EXConfigs.wenscConfig.isSpawnExchanger.ConfigValue) {
                    if (target instanceof EntityPlayer) {
                        if (!haveTryToSpawnExchanger) {
                            if (rand.nextInt(20) == 0) {
                                EntityExchanger entityExchanger = new EntityExchanger(this.worldObj);
                                entityExchanger.setPosition(this.posX, this.posY, this.posZ);
                                entityExchanger.refreshDespawnCounter(-9600);
                                this.worldObj.spawnEntityInWorld(entityExchanger);
                                entityExchanger.onSpawnWithEgg(null);
                                entityExchanger.setAttackTarget(this.getTarget());
                                entityExchanger.entityFX(EnumEntityFX.summoned);
                            }
                            this.haveTryToSpawnExchanger = true;
                        }
                    }
                }
            }
        }
    }
}
