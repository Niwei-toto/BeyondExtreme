package net.moddedmite.mitemod.bex.entity;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import cn.wensc.mitemod.extreme.entity.EntityZombieDoor;
import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import cn.wensc.mitemod.extreme.util.EXConfigs;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class EntityZombieDoorLord extends EntityZombieDoor {
    private int spawnCounter;
    private int spawnSums;
    private boolean haveTryToSpawnExchanger = false;
    private int fx_counter;

    public EntityZombieDoorLord(World par1World) {
        super(par1World);
    }

    protected void addRandomEquipment() {
        int day = Math.min(net.xiaoyu233.mitemod.miteite.util.Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        this.setCurrentItemOrArmor(0, (new ItemStack(BEXItems.doorVibranium, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(1, (new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_HELMET, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(2, (new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_CHESTPLATE, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(3, (new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_LEGGINGS, 1)).randomizeForMob(this, day > 64));
        this.setCurrentItemOrArmor(4, (new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_BOOTS, 1)).randomizeForMob(this, day > 64));
        this.addPotionEffect(new PotionEffect(1, 2147483647, 0));
        this.addPotionEffect(new PotionEffect(5, 2147483647, 0));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = Math.min(net.xiaoyu233.mitemod.miteite.util.Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        double x = (double)(day / 8 - 8);
        double rate = 0.5 + x / (20.0 + Math.abs(x));
        int healthRate = Math.min(day / 16, 10);
        this.setEntityAttribute(SharedMonsterAttributes.attackDamage, rate * 50.0);
        this.setEntityAttribute(SharedMonsterAttributes.maxHealth, rate * 500.0 + (double)(healthRate * 20));
        this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.6D);
    }

    public boolean canBeDisarmed() {
        return false;
    }

    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
            this.dropItem(BEXItems.voucherVibraniumDoor);
            int day = this.getWorld().getDayOfOverworld();
            int diamond_count = (day / 32 > 3 ? 3 : day / 32) * 2;
            for (int i1 = 0; i1 < diamond_count; ++i1) {
                this.dropItem(EXItemsRegistryInit.fancyRed);
            }
        }

    }

    public boolean canCatchFire() {
        return false;
    }

    @Override
    public boolean isVillager() {
        return false;
    }

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
                if (this.spawnSums < 30) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityRevenant zombie = new EntityRevenant(this.worldObj);
                        if (zombie.entityId == 211) {
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
        if (!this.getWorld().isRemote) {
//            EntityLiving target = this.getAttackTarget();
            if (target instanceof EntityPlayer) {
                if (this.spawnSums < 30) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityZombie zombie = new EntityZombieDoorWood(this.worldObj);
                        if (zombie.entityId == 211) {
                            return;
                        }

                        zombie.setPosition(this.posX, this.posY, this.posZ);
                        zombie.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld(zombie);
                        zombie.onSpawnWithEgg((EntityLivingData) null);
                        zombie.addRandomWeapon();
                        zombie.setAttackTarget(this.getTarget());
                        zombie.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
            }
        }

                if (EXConfigs.wenscConfig.isSpawnExchanger.ConfigValue && !this.haveTryToSpawnExchanger) {
                    if (this.spawnSums < 10) {
                        if (this.spawnCounter < 20) {
                            ++this.spawnCounter;
                            EntityExchanger entityExchanger = new EntityExchanger(this.worldObj);
                            entityExchanger.setPosition(this.posX, this.posY, this.posZ);
                            entityExchanger.refreshDespawnCounter(-9600);
                            this.worldObj.spawnEntityInWorld(entityExchanger);
                            entityExchanger.onSpawnWithEgg((EntityLivingData) null);
                            entityExchanger.setAttackTarget(this.getTarget());
                            entityExchanger.entityFX(EnumEntityFX.summoned);
                        }

                        this.haveTryToSpawnExchanger = true;
                    }
                }
            }
        }

    }
    @Override
    public boolean canNeverPickUpItem(Item item_on_ground) {
        return true;
    }
    @Override
    public boolean isHarmedByLava() {
        return false;
    }

    @Override
    public void setInWeb() {

    }
    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    public boolean canBeKnockedBack() {
        return false;
    }

    @Override
    public boolean handleLavaMovement() {
        return false;
    }

    @Override
    public float getReach() {
        return 3.0F;
    }
}
