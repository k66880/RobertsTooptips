/**
 * Copyright (c) 2025 Robert Wu
 * 
 * MIT License
 */
package com.robertsworks.robertstooltips.Tooltip;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Pair;
import com.robertsworks.robertstooltips.util.RGTHelper;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

public class FoodAttributes {
    private static final DecimalFormat df = new DecimalFormat("#.##");

    /** 当前有没有食物属性 */
    public boolean hasFoodProperties = false;
    /** 恢复饥饿值 */
    public int hunger = 0;
    /** 恢复饱和度 */
    public float saturation = 0;
    /** 附带效果 */
    public List<FoodEffect> effects = new ArrayList<>();

    public String formatHunger() {
        return df.format(hunger);
    }

    public String formatSaturation() {
        return df.format(saturation);
    }

    public static FoodAttributes LoadFromItemStack(ItemStack stack) {
        FoodAttributes res = new FoodAttributes();

        FoodProperties foodProperties = RGTHelper.getFoodProperties(stack.getItem());
        if (foodProperties != null) {
            res.hasFoodProperties = true;

            // 恢复饥饿值
            res.hunger = foodProperties.getNutrition();

            // 恢复饱和度
            res.saturation = foodProperties.getSaturationModifier();

            // 附带效果
            for (Pair<MobEffectInstance, Float> possibleEffect : foodProperties.getEffects()) {
                FoodEffect foodEffect = FoodEffect.LoadFromPossibleEffect(possibleEffect);
                res.effects.add(foodEffect);
            }
        }

        return res;
    }
}
