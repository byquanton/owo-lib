package io.wispforest.owo.mixin.itemgroup;

import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.util.pond.OwoItemExtensions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(Item.class)
public class ItemMixin implements OwoItemExtensions {

    @Nullable
    protected ItemGroup owo$group = null;

    @Unique
    private int owo$tab = -1;

    @Unique
    private BiConsumer<Item, DefaultedList<ItemStack>> owo$stackGenerator = OwoItemGroup.DEFAULT_STACK_GENERATOR;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void grabTab(Item.Settings settings, CallbackInfo ci) {
        if (settings instanceof OwoItemSettings owoSettings) {
            this.owo$tab = owoSettings.tab();
            this.owo$stackGenerator = owoSettings.stackGenerator();
        }
    }

    @Override
    public int owo$tab() {
        return owo$tab;
    }

    @Override
    public BiConsumer<Item, DefaultedList<ItemStack>> owo$stackGenerator() {
        return owo$stackGenerator;
    }

    @Override
    public void owo$setGroup(ItemGroup group) {
        this.owo$group = group;
    }
}
