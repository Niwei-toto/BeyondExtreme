package net.moddedmite.mitemod.bex.register;

import cn.wensc.mitemod.extreme.register.EXItemsRegistryInit;
import moddedmite.rustedironcore.api.block.WorkbenchBlock;
import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.minecraft.*;
import net.moddedmite.mitemod.bex.BEXInit;
import net.moddedmite.mitemod.bex.block.BlockBEXAnvil;
import net.moddedmite.mitemod.bex.block.BlockBEXWorkbench;
import net.moddedmite.mitemod.bex.block.BlockFu;
import net.moddedmite.mitemod.bex.block.BlockVolcanoStone;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;
import net.xiaoyu233.fml.util.ReflectHelper;
import net.xiaoyu233.mitemod.miteite.block.MITEITEBlockRegistryInit;
import net.xiaoyu233.mitemod.miteite.item.material.Materials;

import static net.moddedmite.mitemod.bex.register.BEXItems.*;

public class BEXBlocks extends Block {
    public static final Block vibraniumDoor = ReflectHelper.createInstance(BlockDoor.class, new Class[]{int.class, Material.class}, IdUtil.getNextBlockID(), Materials.vibranium).setStepSound(soundMetalFootstep);
    public static final BlockOreStorage blockInfinity = (BlockOreStorage) new BlockOreStorage(IdUtil.getNextBlockID(), BEXMaterials.infinity).setCreativeTab(tabBEX);
    public static final BlockAnvil anvilInfinity = new BlockBEXAnvil(IdUtil.getNextBlockID(), BEXMaterials.infinity);
    public static final Block volcanoStone = new BlockVolcanoStone(IdUtil.getNextBlockID());
    public static final BlockSand volcanoSand = (BlockSand) new BlockSand(IdUtil.getNextBlockID()).setHardness(0.4F).setCreativeTab(tabBEX);
    public static final Block volcanoCobblestone = ReflectHelper.createInstance(Block.class, new Class[]{int.class, Material.class, BlockConstants.class}, IdUtil.getNextBlockID(), BEXMaterials.stone, new BlockConstants())
            .setCreativeTab(tabBEX).setHardness(2.0F);
    public static final Block volcanoAshes = ReflectHelper.createInstance(Block.class, new Class[]{int.class, Material.class, BlockConstants.class}, IdUtil.getNextBlockID(), BEXMaterials.sand, new BlockConstants())
            .setCreativeTab(tabBEX).setStepSound(soundSandFootstep);
    public static final Block volcanoEmeraldOre = new BlockOre(IdUtil.getNextBlockID(), Material.emerald, 3).setHardness(4.0F).setCreativeTab(tabBEX);
    public static final Block volcanoMithrilOre = new BlockOre(IdUtil.getNextBlockID(), Material.mithril, 3).setHardness(4.0F).setCreativeTab(tabBEX);
    public static final Block volcanoDiamondOre = new BlockOre(IdUtil.getNextBlockID(), Material.diamond, 3).setHardness(4.0F).setCreativeTab(tabBEX);
    public static final BlockFu dragonSpawn = new BlockFu(IdUtil.getNextBlockID());
//    public static final Block volcanoGravel = (ReflectHelper.createInstance(BlockVolcanoGravel.class, new Class[] {int.class,Material.class}, IdUtil.getNextBlockID(), BEXMaterials.sand)).setUnlocalizedName("blockVolcanoGravel");
    public static final WorkbenchBlock infinityWorkBench = (WorkbenchBlock) new BlockBEXWorkbench(IdUtil.getNextBlockID(), BEXMaterials.infinity, Float.MAX_VALUE, Materials.vibranium);

    protected BEXBlocks(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    public static void registerBlocks(ItemRegistryEvent event) {
        registerBlock(event, "door_vibranium", vibraniumDoor);

        registerAnvil(event, "anvil_infinity",  anvilInfinity);
        anvilInfinity.stepSound = Block.soundAnvilFootstep;
        registerBlock(event, "block_infinity", blockInfinity);
        registerBlock(event, "toolbench.infinity", infinityWorkBench);

        registerBlock(event, "volcano/volcano_stone", volcanoStone);
        registerBlock(event, "volcano/volcano_cobble_stone", volcanoCobblestone);
        registerBlock(event, "volcano/volcano_sand", volcanoSand);
//        registerBlock(event, "volcano/volcano_gravel", volcanoGravel);
        registerBlock(event, "volcano/volcano_ashes", volcanoAshes);
        registerBlock(event, "volcano/volcano_diamond_ore", volcanoDiamondOre);
        registerBlock(event, "volcano/volcano_mithril_ore", volcanoMithrilOre);
        registerBlock(event, "volcano/volcano_emerald_ore", volcanoEmeraldOre);

        registerBlock(event, "china_fu", dragonSpawn);
    }

    private static Block registerAnvil(ItemRegistryEvent event, String resourceLocation, BlockAnvil anvil) {
        event.registerAnvil(BEXInit.BEXNameSpace, resourceLocation, anvil);
        return anvil;
    }

    private static Block registerBlock(ItemRegistryEvent event, String resourceLocation, Block block) {
        event.registerItemBlock(BEXInit.BEXNameSpace, BEXInit.BEXResourceId + resourceLocation, resourceLocation, block);
        return block;
    }

    private static Block registerBlock(ItemRegistryEvent event, String resourceLocation, String name, Block block) {
        event.registerItemBlock(BEXInit.BEXNameSpace, BEXInit.BEXResourceId + resourceLocation, name, block);
        return block;
    }

    private static Block registerBlock(ItemRegistryEvent event, Block block) {
        event.registerItemBlock(BEXInit.BEXNameSpace, block);
        return block;
    }


    public static void registerRecipes(CraftingRecipeRegisterEvent register) {
        register.registerShapelessRecipe(new ItemStack(blockInfinity), true,
                infinityingot, infinityingot, infinityingot,
                infinityingot, Item.netherStar, infinityingot,
                infinityingot, infinityingot, infinityingot
        );
        register.registerShapedRecipe(new ItemStack(anvilInfinity), true,
                "IBI",
                " C ",
                "CAC",
                'I', infinityingot,
                'B', blockInfinity,
                'C', coinVibranium,
                'A', MITEITEBlockRegistryInit.anvilVibranium
        );
        register.registerShapedRecipe(new ItemStack(dragonSpawn), true,
                "GLZ",
                "ACP",
                'G', BEXItems.voucherGoldBodyCore,
                'L', EXItemsRegistryInit.voucherClubCore,
                'Z', BEXItems.voucherZombieBoss,
                'A', BEXItems.voucherUltimateAnnihilation,
                'C', EXItemsRegistryInit.voucherCore,
                'P', BEXItems.voucherSpiderQueen
        );
        FurnaceRecipes.smelting().addSmelting(BEXBlocks.volcanoEmeraldOre.blockID, new ItemStack(Item.emerald));
        FurnaceRecipes.smelting().addSmelting(BEXBlocks.volcanoDiamondOre.blockID, new ItemStack(Item.diamond));
        FurnaceRecipes.smelting().addSmelting(BEXBlocks.volcanoMithrilOre.blockID, new ItemStack(Item.ingotMithril));
    }
}