package net.moddedmite.mitemod.bex.event.listener;

import cn.wensc.mitemod.extreme.entity.EntityZombieDoor;
import moddedmite.rustedironcore.api.event.events.SpawnConditionRegisterEvent;
import net.moddedmite.mitemod.bex.entity.EntityZombieDoorDeep;
import net.moddedmite.mitemod.bex.entity.EntityZombieDoorLord;

import java.util.function.Consumer;

public class BEXSpawnCondition implements Consumer<SpawnConditionRegisterEvent> {
    @Override
    public void accept(SpawnConditionRegisterEvent event) {
        event.register(EntityZombieDoor.class, (world, x, y, z) -> {
            if (world.isOverworld() && y >= 30) {
                return EntityZombieDoor.class;
            }
            if (world.isUnderworld() || y < 30) {
                return EntityZombieDoorDeep.class;
            }
            return EntityZombieDoor.class;
        });
    }
}

