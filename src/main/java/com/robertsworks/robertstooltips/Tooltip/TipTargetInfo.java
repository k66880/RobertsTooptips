package com.robertsworks.robertstooltips.Tooltip;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class TipTargetInfo {
    /** 目标物品堆栈 */
    public ItemStack stack;

    /** 目标物品的注册ID */
    public ResourceLocation regid;

    public TipTargetInfo(ItemStack stack) {
        this.stack = stack;
        var item = stack.getItem();
        regid = ForgeRegistries.ITEMS.getKey(item);
    }

    public String getFullId() {
        return regid.toString();
    }

    /** 获取物品注册ID的ModID */
    public String getModId() {
        return regid.getNamespace();
    }

    /** 获取物品注册ID（不含ModID） */
    public String getItemId() {
        return regid.getPath();
    }
}
