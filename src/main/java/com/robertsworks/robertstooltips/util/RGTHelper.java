/**
 * Copyright (c) 2025 Robert Wu
 * 
 * MIT License
 */
package com.robertsworks.robertstooltips.util;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RGTHelper {
    /**
     * 将等级数转换为罗马数字
     * @param level 等级数
     * @return
     */
    public static String formatRomanNumber(int level) {
        if(level < 1) return "";
        final String[] ROMAN_SYMBOLS = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return (level <= 10) ? ROMAN_SYMBOLS[level] : String.valueOf(level);
    }
    
    /**
     * 将游戏刻转换为分钟:秒格式
     * @param ticks 游戏刻数（原版药水效果通常为负数时需要特殊处理）
     * @return 格式化后的时间字符串（如 02:15）
     */
    public static String ticksToMMSS(int ticks) {
        // 处理无限持续时间（如创造模式物品）
        if (ticks == 32767) return "∞";

        // 处理无效输入
        if (ticks <= 0) return "00:00";

        // 转换为秒（原版药水持续时间计算方式）
        int totalSeconds = (ticks + 19) / 20; // 原版四舍五入方式
        
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        // 根据原版显示规则优化
        if (minutes > 0) {
            return String.format("%02d:%02d", minutes, seconds);
        } else {
            return String.format("00:%02d", seconds); // 保持两位数显示
        }
    }

    /** 判断指定的物品是否可食用 */
    public static boolean isEdible(Item item) {
        return getFoodProperties(item) != null;
    }

    /** 获取指定的物品的食物属性 */
    @SuppressWarnings("deprecation")
    public static FoodProperties getFoodProperties(Item item) {
        return item.getFoodProperties();
    }

    /** 判断指定的物品堆栈是否不可破坏 */
    public static boolean isUnbreakable(ItemStack stack) {
        if (!stack.isDamageableItem()) return true;
        return stack.getTag() != null && stack.getTag().getBoolean("Unbreakable");
    }
}
