package net.moddedmite.mitemod.bex.mixin.compat.entity;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.util.BEXConfigs;
import net.xiaoyu233.mitemod.miteite.entity.EntityAncientDragon;
import net.moddedmite.mitemod.bex.register.BEXItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityAncientDragon.class)
public class EntityAncientDragonMixin extends EntityMob {

    @Unique private boolean haveTryToSpawnExchanger = false;

    public EntityAncientDragonMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addAIHitBox(World par1World, CallbackInfo ci) {
        this.setSize(0.1F, 0.1F);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.tasks.addTask(2, new EntityAIMoveToFoodItem(this, 1.0F, true));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 10, true));
        this.tasks.addTask(3, new EntityAIMoveToTree(this, 1.0F));

    }


    /**
     * @author
     * @reason
     */
    @Overwrite
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(SharedMonsterAttributes.followRange, 12800.0D);
        this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 600.0D);
        this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 10.0D);
        this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 60.0D);
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
            this.dropItem(BEXItems.AvariceMeatBalls);
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
            if (BEXConfigs.wenscConfig.isSpawnExchanger.ConfigValue) {
                if(haveTryToSpawnExchanger == false) {
                    if( rand.nextInt(10) == 0) {
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

    @Override
    public int getExperienceValue() {
        return super.getExperienceValue() * 6;
    }
}
