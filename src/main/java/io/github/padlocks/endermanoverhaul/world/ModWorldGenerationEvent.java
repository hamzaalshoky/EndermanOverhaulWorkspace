package io.github.padlocks.endermanoverhaul.world;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = EndermanOverhaul.MOD_ID)
public class ModWorldGenerationEvent {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModEntityGeneration.onEntitySpawn(event);
    }
}
