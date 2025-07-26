package net.moddedmite.mitemod.bex.event;

import net.xiaoyu233.fml.reload.event.MITEEvents;

public class BEXEventListeners {
    public static void registerAllEvents() {
        MITEEvents.MITE_EVENT_BUS.register(new BEXFishEvents());
        BEXRICEvents.register();
    }
}
