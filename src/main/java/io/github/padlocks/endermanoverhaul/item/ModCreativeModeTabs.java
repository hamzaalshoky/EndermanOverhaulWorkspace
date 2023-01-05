package io.github.padlocks.endermanoverhaul.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab ENDERMAN_OVERHAUL_TAB = new CreativeModeTab("enderman_overhaul_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SAVANNA_ENDERMAN_SPAWN_EGG.get());
        }
    };
}
