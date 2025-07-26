package net.moddedmite.mitemod.bex.entity;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import cn.wensc.mitemod.extreme.entity.EntityZombieDoor;
import cn.wensc.mitemod.extreme.util.Constant;
import cn.wensc.mitemod.extreme.util.EXConfigs;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.SoftOverride;

public class EntityZombieDoorDeep extends EntityZombieDoor {
    private int spawnCounter;
    private int spawnSums;
    private boolean haveTryToSpawnExchanger = false;

    public EntityZombieDoorDeep(World par1World) {
        super(par1World);
    }

    @Override
    protected void addRandomEquipment() {
        int day = Math.min(Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        Item[] doorList = new Item[] {Item.doorAdamantium, Item.doorAncientMetal, Item.doorMithril};
        this.setCurrentItemOrArmor(0, (new ItemStack(doorList[Constant.EXRandom.nextInt(doorList.length - 1)], 1)).randomizeForMob(this, false));
        this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2147483647, 0));
        if(day / 32 >= 1) {
            this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2147483647, 0));
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = Math.min(Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        double x = day / 8 - 8;
        double rate = (0.5+ x / (20 + Math.abs(x)));
        int healthRate = Math.min(day / 16, 10);
        this.setEntityAttribute(SharedMonsterAttributes.attackDamage, rate * 60);
        this.setEntityAttribute(SharedMonsterAttributes.maxHealth, rate * 60 + healthRate * 20);
        this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.3D);
    }

    @SoftOverride
    public boolean canBeDisarmed() {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
            EntityLivingBase target = this.getAttackTarget();
            if(target instanceof EntityPlayer) {
                if (this.spawnSums < 6) {
                    if (this.spawnCounter < 20) {
                        ++this.spawnCounter;
                    } else {
                        EntityRevenant revenant = new EntityRevenant(this.worldObj);
                        if (revenant.entityId == 218) {
                            return;
                        }
                        revenant.setPosition(this.posX, this.posY, this.posZ);
                        revenant.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld((Entity)revenant);
                        revenant.onSpawnWithEgg(null);
                        revenant.addRandomWeapon();
                        revenant.setAttackTarget(this.getTarget());
                        revenant.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
                if (EXConfigs.wenscConfig.isSpawnExchanger.ConfigValue) {
                    if (haveTryToSpawnExchanger == false) {
                        if(rand.nextInt(10) == 0) {
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
