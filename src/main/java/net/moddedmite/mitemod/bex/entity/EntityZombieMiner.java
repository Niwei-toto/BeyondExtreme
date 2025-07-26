package net.moddedmite.mitemod.bex.entity;

import cn.wensc.mitemod.extreme.entity.EntityExchanger;
import cn.wensc.mitemod.extreme.util.EXConfigs;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class EntityZombieMiner extends EntityZombie {
    public EntityZombieMiner(World par1World) {
        super(par1World);
        this.tasks.addTask(3, new EntityAISeekLitTorch(this, 1.0F));
    }

    private int fx_counter;
    private int spawnCounter;
    private int spawnSums;
    private boolean haveTryToSpawnExchanger = false;


    @Override
    protected void addRandomEquipment() {
        int day = Math.min(net.xiaoyu233.mitemod.miteite.util.Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        this.setCurrentItemOrArmor(0, (new ItemStack(MITEITEItemRegistryInit.VIBRANIUM_PICKAXE, 1)).randomizeForMob(this, day > 64));
        this.addPotionEffect(new PotionEffect(1, 2147483647, 0));
        this.addPotionEffect(new PotionEffect(3, 2147483647, 127));
        this.addPotionEffect(new PotionEffect(5, 2147483647, 0));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = Math.min(net.xiaoyu233.mitemod.miteite.util.Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        double x = day / 7 - 7;
        double rate = (0.5 + x / (20 + Math.abs(x)));
        int healthRate = Math.min(day / 16, 10);
        this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 8.0D + (double) day / 16.0D);
        this.setEntityAttribute(SharedMonsterAttributes.maxHealth, rate * 50 + healthRate * 10);
        this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.3D);
    }

    @Override
    public boolean canNeverPickUpItem(Item item_on_ground) {
        return true;
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
            this.dropItem(BEXItems.voucherZombieMiner);
            int day = this.getWorld().getDayOfOverworld();
            int diamond_count = (day / 32) >= 3 ? 3 : ((day / 32) + 1);
            for (int i1 = 0; i1 < diamond_count; i1++) {
                this.dropItem(Item.emerald);
            }
        }
    }

    @Override
    public boolean getCanSpawnHere(boolean perform_light_check) {
        return super.getCanSpawnHere(perform_light_check) && this.worldObj.getDayOfOverworld() >= 10;
    }

    @Override
    public boolean canCatchFire() {
        return false;
    }

    @Override
    public boolean isVillager() {
        return false;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
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
                        EntityEarthElemental elemental = new EntityEarthElemental(this.worldObj);
                        if (elemental.entityId == 207) {
                            return;
                        }
                        elemental.setPosition(this.posX, this.posY, this.posZ);
                        elemental.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld((Entity) elemental);
                        elemental.onSpawnWithEgg(null);
                        elemental.setAttackTarget(this.getTarget());
                        elemental.entityFX(EnumEntityFX.summoned);
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
