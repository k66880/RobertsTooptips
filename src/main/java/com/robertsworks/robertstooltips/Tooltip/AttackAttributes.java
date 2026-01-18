/**
 * Copyright (c) 2025 Robert Wu
 * 
 * MIT License
 */
package com.robertsworks.robertstooltips.Tooltip;

import java.text.DecimalFormat;

import com.google.common.collect.Multimap;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class AttackAttributes {
    private static final double BASE_ATTACK_DAMAGE = 1.0;
    private static final double BASE_ATTACK_SPEED = 4.0;
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public boolean hasMainHandDamage = false;
    public boolean hasMainHandAttackSpeed = false;
    public boolean hasMainHandAttackKnockback = false;

    public boolean hasOffHandDamage = false;
    public boolean hasOffHandAttackSpeed = false;
    public boolean hasOffHandAttackKnockback = false;

    public double mainHandDamage = 0;
    public double mainHandAttackSpeed = 0;
    public double mainHandAttackKnockback = 0;
    
    public double offHandDamage = 0;
    public double offHandAttackSpeed = 0;
    public double offHandAttackKnockback = 0;

    public String formatMainHandDamage() {
        return df.format(BASE_ATTACK_DAMAGE + mainHandDamage);
    }

    public String formatMainHandAttackSpeed() {
        return df.format(BASE_ATTACK_SPEED + mainHandAttackSpeed);
    }

    public String formatMainHandAttackKnockback() {
        return "+" + df.format(mainHandAttackKnockback);
    }

    public String formatOffHandDamage() {
        return df.format(BASE_ATTACK_DAMAGE + offHandDamage);
    }

    public String formatOffHandAttackSpeed() {
        return df.format(BASE_ATTACK_SPEED + offHandAttackSpeed);
    }

    public String formatOffHandAttackKnockback() {
        return "+" + df.format(offHandAttackKnockback);
    }

    public static AttackAttributes LoadFromItemStack(ItemStack stack) {
        AttackAttributes res = new AttackAttributes();

        Multimap<Attribute, AttributeModifier> mainhandAttrs = stack.getAttributeModifiers(EquipmentSlot.MAINHAND);
        Multimap<Attribute, AttributeModifier> offhandAttrs = stack.getAttributeModifiers(EquipmentSlot.OFFHAND);
        res.hasMainHandDamage = mainhandAttrs.containsKey(Attributes.ATTACK_DAMAGE);
        res.hasMainHandAttackSpeed = mainhandAttrs.containsKey(Attributes.ATTACK_SPEED);
        res.hasMainHandAttackKnockback = mainhandAttrs.containsKey(Attributes.ATTACK_KNOCKBACK);
        res.hasOffHandDamage = offhandAttrs.containsKey(Attributes.ATTACK_DAMAGE);
        res.hasOffHandAttackSpeed = offhandAttrs.containsKey(Attributes.ATTACK_SPEED);
        res.hasOffHandAttackKnockback = offhandAttrs.containsKey(Attributes.ATTACK_KNOCKBACK);

        if (res.hasMainHandDamage) 
            res.mainHandDamage = getAttributeValue(mainhandAttrs, Attributes.ATTACK_DAMAGE);
        if (res.hasMainHandAttackSpeed) 
            res.mainHandAttackSpeed = getAttributeValue(mainhandAttrs, Attributes.ATTACK_SPEED);
        if (res.hasMainHandAttackKnockback) 
            res.mainHandAttackKnockback = getAttributeValue(mainhandAttrs, Attributes.ATTACK_KNOCKBACK);

        if (res.hasOffHandDamage) 
            res.offHandDamage = getAttributeValue(mainhandAttrs, Attributes.ATTACK_DAMAGE);
        if (res.hasOffHandAttackSpeed) 
            res.offHandAttackSpeed = getAttributeValue(mainhandAttrs, Attributes.ATTACK_SPEED);
        if (res.hasOffHandAttackKnockback) 
            res.offHandAttackKnockback = getAttributeValue(mainhandAttrs, Attributes.ATTACK_KNOCKBACK);

        return res;
    }

    private static double getAttributeValue(Multimap<Attribute, AttributeModifier> attributes, Attribute attribute) {
        return attributes.get(attribute).stream()
                .mapToDouble(AttributeModifier::getAmount)
                .sum();
    }
}
