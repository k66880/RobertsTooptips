package com.robertsworks.robertstooltips.Tooltip;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class HarvestLevel {
    /** 获取指定挖掘工具的挖掘等级（1~4） */
    public static int getHarvestLevel(ItemStack stack) {
        return detectMiningLevelByTesting(stack);
    }
    
    public static Component getHarvestLevelName(int level) {
        if (level == 1) return Component.translatable("tooltip.roberts_game_tweaks.harvest_level_1");
        if (level == 2) return Component.translatable("tooltip.roberts_game_tweaks.harvest_level_2");
        if (level == 3) return Component.translatable("tooltip.roberts_game_tweaks.harvest_level_3");
        if (level == 4) return Component.translatable("tooltip.roberts_game_tweaks.harvest_level_4");
        // if (level == 5) return Component.translatable("tooltip.roberts_game_tweaks.harvest_level_5");
        return Component.translatable("tooltip.roberts_game_tweaks.harvest_level_0");
    }

    private static int detectMiningLevelByTesting(ItemStack stack) {
        // 通过测试工具对不同硬度方块的挖掘能力来判断等级
        if (canMineBlock(stack, Blocks.OBSIDIAN.defaultBlockState())) return 4;
        if (canMineBlock(stack, Blocks.DIAMOND_ORE.defaultBlockState())) return 3;
        if (canMineBlock(stack, Blocks.IRON_ORE.defaultBlockState())) return 2;
        if (canMineBlock(stack, Blocks.STONE.defaultBlockState())) return 1;
        return -1; // 无法挖掘石头
    }

    private static boolean canMineBlock(ItemStack stack, BlockState state) {
        return stack.isCorrectToolForDrops(state);
    }
}
