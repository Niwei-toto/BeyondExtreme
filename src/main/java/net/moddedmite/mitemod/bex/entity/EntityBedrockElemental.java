package net.moddedmite.mitemod.bex.entity;

import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
import net.xiaoyu233.mitemod.miteite.util.Configs;

public class EntityBedrockElemental extends EntityEarthElemental {
    private int spawnCounter;
    private int spawnSums;

    public EntityBedrockElemental(World world) {
        super(world);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = Math.min(Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        double x = (double) (day / 8 - 8);
        double rate = 0.5 + x / (20.0 + Math.abs(x));
        this.setEntityAttribute(SharedMonsterAttributes.attackDamage, rate * 50.0);
        this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 300);
        this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.35D);
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player){
            this.dropItem(BEXItems.voucherBedrock);
//            this.dropItem(Item.getItem(Block.bedrock));
            this.dropItem(EXItemsRegistryInit.fancyRed, 3);
        }
    }

    @Override
    public boolean isMagma() {
            return false;
    }

    @Override
    public boolean isImmuneTo(DamageSource damage_source) {
        ItemStack item_stack = damage_source.getItemAttackedWith();
        boolean noNull = item_stack != null && item_stack.getItem() instanceof ItemTool;
        if (noNull && item_stack.itemID == BEXItems.infinitySword.itemID ||
                noNull && item_stack.itemID == MITEITEItemRegistryInit.VIBRANIUM_WAR_HAMMER.itemID ||
                noNull && item_stack.itemID == MITEITEItemRegistryInit.VIBRANIUM_PICKAXE.itemID ||
                noNull && item_stack.itemID == BEXItems.enchantPickaxe.itemID ||
                noNull && item_stack.itemID == BEXItems.infinityPickaxe.itemID) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public EntityDamageResult attackEntityAsMob(Entity target) {
        if (target != null && target.isEntityAlive()){
            target.setFire(20);
        }
        return super.attackEntityAsMob(target);
    }
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
            EntityLivingBase target = this.getAttackTarget();
            if (target instanceof EntityPlayer) {
                if (this.spawnSums < 20) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityEarthElemental EarthElemental = new EntityEarthElemental(this.worldObj);
                        if (EarthElemental.entityId == 211) {
                            return;
                        }
                        EarthElemental.setPosition(this.posX, this.posY, this.posZ);
                        EarthElemental.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld(EarthElemental);
                        EarthElemental.onSpawnWithEgg((EntityLivingData) null);
                        EarthElemental.setAttackTarget(this.getTarget());
                        EarthElemental.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
            }
        }
        EntityLivingBase target = this.getAttackTarget();
        if (target instanceof EntityPlayer) {
            if (this.spawnSums < 10) {
                if (this.spawnCounter < 200) {
                    ++this.spawnCounter;
                } else {
                    EntityFireElemental FireElemental = new EntityFireElemental(this.worldObj);
                    if (FireElemental.entityId == 211) {
                        return;
                    }
                    FireElemental.setPosition(this.posX, this.posY, this.posZ);
                    FireElemental.refreshDespawnCounter(-9600);
                    this.worldObj.spawnEntityInWorld(FireElemental);
                    FireElemental.onSpawnWithEgg((EntityLivingData) null);
                    FireElemental.setAttackTarget(this.getTarget());
                    FireElemental.entityFX(EnumEntityFX.summoned);
                    this.spawnCounter = 0;
                    ++this.spawnSums;
                }
            }
        }
    }
    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    public boolean canBeKnockedBack() {
        return false;
    }

}
