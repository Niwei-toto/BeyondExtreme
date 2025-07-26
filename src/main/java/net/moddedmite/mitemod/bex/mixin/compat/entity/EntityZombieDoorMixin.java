package net.moddedmite.mitemod.bex.mixin.compat.entity;

import cn.wensc.mitemod.extreme.entity.EntityZombieDoor;
import net.minecraft.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Arrays;

@Mixin(EntityZombieDoor.class)
public class EntityZombieDoorMixin {

//    @Inject(
//            method = "addRandomEquipment",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Ljava/util/Random;nextInt(I)I",
//                    shift = At.Shift.BEFORE
//            ),
//            locals = LocalCapture.CAPTURE_FAILHARD
//    )
//    private void modifyDoorList(CallbackInfo ci, int day, Item[] doorList) {
//        doorList = (Item[]) Arrays.stream(doorList).filter(item -> item != Item.doorAdamantium).toArray();
//    }
}
