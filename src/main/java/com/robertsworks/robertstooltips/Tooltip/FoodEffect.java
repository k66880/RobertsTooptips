/**
 * Copyright (c) 2025 Robert Wu
 * 
 * MIT License
 */
package com.robertsworks.robertstooltips.Tooltip;

import com.mojang.datafixers.util.Pair;
import com.robertsworks.robertstooltips.Config.ModConfigCore;
import com.robertsworks.robertstooltips.util.RGTHelper;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;

public class FoodEffect {
    /** 效果名称的多语言键名 */
    public String descriptionId = null;
    /** 效果等级 */
    public int amplifier = 0;
    /** 效果持续时间(Tick数，20Tick = 1s) */
    public int duration = 0;
    /** 效果出现概率(0~1) */
    public float probability = 0;

    public MutableComponent makeLine() {
        MutableComponent line = Component.empty();
        if (!ModConfigCore.tooltipPrefixWord.isEmpty())
            line.append(Component.literal(ModConfigCore.tooltipPrefixWord).withStyle(ChatFormatting.LIGHT_PURPLE));
        line.append(Component.translatable(descriptionId).withStyle(ChatFormatting.DARK_PURPLE));
        line.append(Component.literal(" " + RGTHelper.formatRomanNumber(amplifier)).withStyle(ChatFormatting.DARK_PURPLE)); // 效果名称和等级
        line.append(Component.literal(" (" + RGTHelper.ticksToMMSS(duration) + ")").withStyle(ChatFormatting.GRAY)); // 持续时间
        if (probability < 1.0f)
            line.append(Component.literal(" [" + formatPercentage(probability) + "]").withStyle(ChatFormatting.GRAY)); // 出现概率
        return line;
    }

    public static String formatPercentage(float decimal) {
        int percentage = (int)(decimal * 100);
        return String.format("%d%%", percentage);
    }

    public static FoodEffect LoadFromPossibleEffect(Pair<MobEffectInstance, Float> possibleEffect) {
        FoodEffect res = new FoodEffect();
        MobEffectInstance effect = possibleEffect.getFirst();
        res.descriptionId = effect.getDescriptionId();
        res.amplifier = effect.getAmplifier() + 1;
        res.duration = effect.getDuration();
        res.probability = possibleEffect.getSecond();
        return res;
    }
}
