/**
 * Copyright (c) 2025 Robert Wu
 * 
 * MIT License
 */
package com.robertsworks.robertstooltips.Config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import com.robertsworks.robertstooltips.RobertsTooltipsMod;
import com.robertsworks.robertstooltips.util.DurabilityTooltipType;

@Mod.EventBusSubscriber(modid = RobertsTooltipsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfigCore
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<String> TOOLTIP_PREFIX_WORD = BUILDER
        .comment("The prefix word of tooltips")
        .define("tooltipPrefixWord", "◆ ");

    private static final ForgeConfigSpec.BooleanValue SHOW_DURABILITY = BUILDER
        .comment("Whether to show the durability info")
        .define("showDurability", true);

    private static final ForgeConfigSpec.ConfigValue<String> DURABILITY_BLACKLIST = BUILDER
        .comment("A blacklist used to specify the items which not required durability info")
        .comment("For example: \"minecraft:wooden_sword, diamond_sword, @tconstruct\"")
        .define("durabilityBlacklist", "@tconstruct");

    private static final ForgeConfigSpec.EnumValue<DurabilityTooltipType> DURABILITY_TOOLTIP_TYPE = BUILDER
        .comment("The display type of durability info")
        .defineEnum("durabilityTooltipType", DurabilityTooltipType.Number);

    private static final ForgeConfigSpec.BooleanValue SHOW_HARVEST_LEVEL = BUILDER
        .comment("Whether to show the harvest level info")
        .define("showHarvestLevel", true);

    private static final ForgeConfigSpec.BooleanValue SHOW_TOOLTIPS_FOR_WEAPONS = BUILDER
        .comment("Whether to show the tooltips for weapons")
        .define("showTooltipsForWeapons", true);

    private static final ForgeConfigSpec.ConfigValue<String> WEAPONS_TOOLTIPS_BLACKLIST = BUILDER
        .comment("A blacklist used to specify the items which not required weapons tooltips")
        .comment("For example: \"minecraft:wooden_sword, diamond_sword, @tconstruct\"")
        .define("weaponsTooltipsBlacklist", "");

    private static final ForgeConfigSpec.BooleanValue SHOW_TOOLTIPS_FOR_ARMORS = BUILDER
        .comment("Whether to show the tooltips for armors")
        .define("showTooltipsForArmors", true);

    private static final ForgeConfigSpec.ConfigValue<String> ARMORS_TOOLTIPS_BLACKLIST = BUILDER
        .comment("A blacklist used to specify the items which not required armors tooltips")
        .comment("For example: \"minecraft:iron_chestplate, diamond_chestplate, @tconstruct\"")
        .define("armorsTooltipsBlacklist", "");

    private static final ForgeConfigSpec.BooleanValue SHOW_ATTRIBUTES_FOR_FOODS = BUILDER
        .comment("Whether to show the attributes tooltips for foods")
        .define("showAttributesForFoods", true);

    private static final ForgeConfigSpec.BooleanValue SHOW_EFFECTS_FOR_FOODS = BUILDER
        .comment("Whether to show the effects tooltips for foods")
        .define("showEffectsForFoods", true);

    private static final ForgeConfigSpec.BooleanValue SHOW_BURN_TIME_FOR_FUEL = BUILDER
        .comment("Whether to show the burn time tooltips for fuel")
        .define("showBurnTimeForFuel", true);

    private static final ForgeConfigSpec.BooleanValue REMOVE_VANILLA_TOOLTIPS = BUILDER
        .comment("Whether to remove the vanilla tooltips")
        .define("removeVanillaTooltips", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    /** 提示信息的前缀字符 */
    public static String tooltipPrefixWord;

    /** 是否显示耐久度信息 */
    public static boolean showDurability;

    /** （当showDurability启用时）耐久度信息的展示方式 */
    public static DurabilityTooltipType durabilityTooltipType;

    /** 耐久度信息黑名单 */
    public static String durabilityBlacklist;

    /** 是否显示挖掘工具的挖掘等级 */
    public static boolean showHarvestLevel;

    /** 是否显示武器的属性信息 */
    public static boolean showTooltipsForWeapons;

    /** 武器属性信息黑名单 */
    public static String weaponsTooltipsBlacklist;

    /** 是否显示防具的属性信息 */
    public static boolean showTooltipsForArmors;

    /** 防具属性信息黑名单 */
    public static String armorsTooltipsBlacklist;

    /** 是否显示食物的基础属性信息 */
    public static boolean showAttributesForFoods;

    /** 是否显示食物的附带效果信息 */
    public static boolean showEffectsForFoods;

    /** 是否显示燃料的燃烧时间 */
    public static boolean showBurnTimeForFuel;

    /** 是否移除原版游戏的武器、工具、防具属性信息显示 */
    public static boolean removeVanillaTooltips;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        tooltipPrefixWord = TOOLTIP_PREFIX_WORD.get();
        showDurability = SHOW_DURABILITY.get();
        durabilityBlacklist = DURABILITY_BLACKLIST.get();
        showHarvestLevel = SHOW_HARVEST_LEVEL.get();
        durabilityTooltipType = DURABILITY_TOOLTIP_TYPE.get();
        showTooltipsForWeapons = SHOW_TOOLTIPS_FOR_WEAPONS.get();
        weaponsTooltipsBlacklist = WEAPONS_TOOLTIPS_BLACKLIST.get();
        showTooltipsForArmors = SHOW_TOOLTIPS_FOR_ARMORS.get();
        armorsTooltipsBlacklist = ARMORS_TOOLTIPS_BLACKLIST.get();
        showAttributesForFoods = SHOW_ATTRIBUTES_FOR_FOODS.get();
        showEffectsForFoods = SHOW_EFFECTS_FOR_FOODS.get();
        showBurnTimeForFuel = SHOW_BURN_TIME_FOR_FUEL.get();
        removeVanillaTooltips = REMOVE_VANILLA_TOOLTIPS.get();
    }
}
