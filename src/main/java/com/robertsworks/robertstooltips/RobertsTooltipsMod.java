/**
 * Copyright (c) 2025 Robert Wu
 * 
 * MIT License
 */
package com.robertsworks.robertstooltips;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.robertsworks.robertstooltips.Config.ModConfigCore;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(RobertsTooltipsMod.MODID)
public class RobertsTooltipsMod {
    public static final String MODID = "roberts_tooltips";

    public static final Logger LOGGER = LogUtils.getLogger();

    public RobertsTooltipsMod() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigCore.SPEC);
    }
}
