package net.moddedmite.mitemod.bex.mixin.compat.entity;

import cn.wensc.mitemod.extreme.util.Constant;
import cn.wensc.mitemod.extreme.util.EXConfigs;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.entity.EntityZombieLord;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityZombieLord.class)
public class EntityZombieLordMixin extends EntityZombie {

    @Shadow private int fx_counter;
    @Unique private int spawnSums;
    @Unique private int spawnCounter;

    public EntityZombieLordMixin(World par1World) {
        super(par1World);
    }

    @WrapOperation(method = "addRandomEquipment", at = @At(value = "INVOKE", target = "Lnet/xiaoyu233/mitemod/miteite/entity/EntityZombieLord;setCurrentItemOrArmor(ILnet/minecraft/ItemStack;)V"))
    protected void addRandomEquipment(EntityZombieLord instance, int i, ItemStack itemStack, Operation<Void> original, @Local(name = "day") int day) {
        Item[] itemList = new Item[]{MITEITEItemRegistryInit.VIBRANIUM_SWORD, MITEITEItemRegistryInit.VIBRANIUM_BATTLE_AXE, MITEITEItemRegistryInit.VIBRANIUM_WAR_HAMMER};
        this.setCurrentItemOrArmor(0, (new ItemStack(itemList[Constant.EXRandom.nextInt(itemList.length - 1)], 1)).randomizeForMob(this, true));
        this.setCurrentItemOrArmor(1, (new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_HELMET, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(2, (new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_CHESTPLATE, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(3, (new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_LEGGINGS, 1)).randomizeForMob(this, day > 64));
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
                        EntityRevenant zombie = new EntityRevenant(this.worldObj);
                        if (zombie.entityId == 207) {
                            return;
                        }
                        zombie.setPosition(this.posX, this.posY, this.posZ);
                        zombie.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld((Entity) zombie);
                        zombie.onSpawnWithEgg(null);
                        zombie.addRandomWeapon();
                        zombie.setAttackTarget(this.getTarget());
                        zombie.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
            }
        }
    }
}
