package net.moddedmite.mitemod.bex.entity;

import net.minecraft.*;

public class EntityZombieDoorWood extends EntityZombie {

    public EntityZombieDoorWood(World par1World) {
        super(par1World);
    }

    @Override
    protected void addRandomEquipment() {
        int day = Math.min(net.xiaoyu233.mitemod.miteite.util.Configs.Entities.ENHANCE_LIMIT.get(), this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0);
        this.setCurrentItemOrArmor(0, (new ItemStack(Item.doorWood, 1)).randomizeForMob(this, day > 64));
        this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2147483647, 0));
        if( day / 32 >= 1) {
            this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2147483647, 0));
        }
    }

    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
    }
}

