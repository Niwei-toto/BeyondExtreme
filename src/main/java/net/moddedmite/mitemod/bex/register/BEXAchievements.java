package net.moddedmite.mitemod.bex.register;

import net.minecraft.Achievement;
import net.xiaoyu233.fml.reload.utils.IdUtil;
import net.xiaoyu233.mitemod.miteite.achievement.MITEITEAchievementRegistryInit;

public class BEXAchievements {
    public static Achievement infinitySword;
    public static Achievement UAKiller;

    public static void registerAchievements() {
        infinitySword = new Achievement(IdUtil.getNextAchievementID(),"infinitySword", 0, 19, BEXItems.infinitySword, MITEITEAchievementRegistryInit.vibraniumAnvil).setSpecial().registerAchievement();
        UAKiller = new Achievement(IdUtil.getNextAchievementID(),"UAKiller", 0, 21, BEXItems.voucherUltimateAnnihilation, infinitySword).setSpecial().registerAchievement();
    }
}
