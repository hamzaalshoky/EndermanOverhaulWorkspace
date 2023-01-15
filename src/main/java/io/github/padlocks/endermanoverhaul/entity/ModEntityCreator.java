package io.github.padlocks.endermanoverhaul.entity;

import io.github.padlocks.endermanoverhaul.EndermanOverhaul;
import io.github.padlocks.endermanoverhaul.client.renderer.BadlandsEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.CaveEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.CrimsonForestEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.DarkOakEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.DesertEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.EndEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.EndIslandsEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.FlowerFieldsEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.IceSpikesEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.MushroomEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.NetherWastesEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.OceanEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.SavannaEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.ScarabRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.SnowyEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.SoulsandValleyEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.SpiritRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.SwampEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.WarpedForestEndermanRenderer;
import io.github.padlocks.endermanoverhaul.client.renderer.WindsweptHillsEndermanRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EndermanOverhaul.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityCreator {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, EndermanOverhaul.MOD_ID);

    // REGESTRIES

    public static final RegistryObject<EntityType<SavannaEndermanEntity>> SAVANNA_ENDERMAN = ENTITY_TYPES.register("savanna_enderman", () -> EntityType.Builder.of(SavannaEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 2.8F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "savanna_enderman").toString()));
    public static final RegistryObject<EntityType<BadlandsEndermanEntity>> BADLANDS_ENDERMAN = ENTITY_TYPES.register("badlands_enderman", () -> EntityType.Builder.of(BadlandsEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 3F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "badlands_enderman").toString()));
    public static final RegistryObject<EntityType<SnowyEndermanEntity>> SNOWY_ENDERMAN = ENTITY_TYPES.register("snowy_enderman", () -> EntityType.Builder.of(SnowyEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 2.8F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "snowy_enderman").toString()));
    public static final RegistryObject<EntityType<ScarabEntity>> SCARAB = ENTITY_TYPES.register("scarab", () -> EntityType.Builder.of(ScarabEntity::new, MobCategory.MONSTER).sized(0.3F, 0.5F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "scarab").toString()));
    public static final RegistryObject<EntityType<SpiritEntity>> SPIRIT = ENTITY_TYPES.register("spirit", () -> EntityType.Builder.of(SpiritEntity::new, MobCategory.MONSTER).sized(0.3F, 0.3F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "spirit").toString()));
    public static final RegistryObject<EntityType<DesertEndermanEntity>> DESERT_ENDERMAN = ENTITY_TYPES.register("desert_enderman", () -> EntityType.Builder.of(DesertEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 3.1F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "desert_enderman").toString()));
    public static final RegistryObject<EntityType<DarkOakEndermanEntity>> DARK_OAK_ENDERMAN = ENTITY_TYPES.register("dark_oak_enderman", () -> EntityType.Builder.of(DarkOakEndermanEntity::new, MobCategory.MONSTER).sized(0.7F, 3.5F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "dark_oak_enderman").toString()));
    public static final RegistryObject<EntityType<IceSpikesEndermanEntity>> ICE_SPIKES_ENDERMAN = ENTITY_TYPES.register("ice_spikes_enderman", () -> EntityType.Builder.of(IceSpikesEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 3.9F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "ice_spikes_enderman").toString()));
    public static final RegistryObject<EntityType<SwampEndermanEntity>> SWAMP_ENDERMAN = ENTITY_TYPES.register("swamp_enderman", () -> EntityType.Builder.of(SwampEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 3.5F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "swamp_enderman").toString()));
    public static final RegistryObject<EntityType<WindsweptHillsEndermanEntity>> WINDSWEPT_HILLS_ENDERMAN = ENTITY_TYPES.register("windswept_hills_enderman", () -> EntityType.Builder.of(WindsweptHillsEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 4.1F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "windswept_hills_enderman").toString()));
    public static final RegistryObject<EntityType<WarpedForestEndermanEntity>> WARPED_FOREST_ENDERMAN = ENTITY_TYPES.register("warped_forest_enderman", () -> EntityType.Builder.of(WarpedForestEndermanEntity::new, MobCategory.MONSTER).sized(0.8F, 2.4F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "warped_forest_enderman").toString()));
    public static final RegistryObject<EntityType<CrimsonForestEndermanEntity>> CRIMSON_FOREST_ENDERMAN = ENTITY_TYPES.register("crimson_forest_enderman", () -> EntityType.Builder.of(CrimsonForestEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 3.1F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "crimson_forest_enderman").toString()));
    public static final RegistryObject<EntityType<SoulsandValleyEndermanEntity>> SOULSAND_VALLEY_ENDERMAN = ENTITY_TYPES.register("soulsand_valley_enderman", () -> EntityType.Builder.of(SoulsandValleyEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 2.5F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "soulsand_valley_enderman").toString()));
    public static final RegistryObject<EntityType<NetherWastesEndermanEntity>> NETHER_WASTES_ENDERMAN = ENTITY_TYPES.register("nether_wastes_enderman", () -> EntityType.Builder.of(NetherWastesEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 2.9F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "nether_wastes_enderman").toString()));
    public static final RegistryObject<EntityType<CaveEndermanEntity>> CAVE_ENDERMAN = ENTITY_TYPES.register("cave_enderman", () -> EntityType.Builder.of(CaveEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 2.6F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "cave_enderman").toString()));
    public static final RegistryObject<EntityType<FlowerFieldsEndermanEntity>> FLOWER_FIELDS_ENDERMAN = ENTITY_TYPES.register("flower_fields_enderman", () -> EntityType.Builder.of(FlowerFieldsEndermanEntity::new, MobCategory.CREATURE).sized(0.5F, 1.5F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "flower_fields_enderman").toString()));
    public static final RegistryObject<EntityType<MushroomEndermanEntity>> MUSHROOM_ENDERMAN = ENTITY_TYPES.register("mushroom_enderman", () -> EntityType.Builder.of(MushroomEndermanEntity::new, MobCategory.CREATURE).sized(0.6F, 2.6F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "mushroom_enderman").toString()));
    public static final RegistryObject<EntityType<EndEndermanEntity>> END_ENDERMAN = ENTITY_TYPES.register("end_enderman", () -> EntityType.Builder.of(EndEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 2.5F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "end_enderman").toString()));
    public static final RegistryObject<EntityType<OceanEndermanEntity>> OCEAN_ENDERMAN = ENTITY_TYPES.register("ocean_enderman", () -> EntityType.Builder.of(OceanEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 2F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "ocean_enderman").toString()));
    public static final RegistryObject<EntityType<EndIslandsEndermanEntity>> END_ISLANDS_ENDERMAN = ENTITY_TYPES.register("end_islands_enderman", () -> EntityType.Builder.of(EndIslandsEndermanEntity::new, MobCategory.MONSTER).sized(0.6F, 4.4F).build(new ResourceLocation(EndermanOverhaul.MOD_ID, "end_islands_enderman").toString()));

    // ATTRIBUTES

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityCreator.SAVANNA_ENDERMAN.get(), SavannaEndermanEntity.setAttributes());
        event.put(ModEntityCreator.BADLANDS_ENDERMAN.get(), BadlandsEndermanEntity.setAttributes());
        event.put(ModEntityCreator.SNOWY_ENDERMAN.get(), SnowyEndermanEntity.setAttributes());
        event.put(ModEntityCreator.SCARAB.get(), ScarabEntity.setAttributes());
        event.put(ModEntityCreator.SPIRIT.get(), SpiritEntity.setAttributes());
        event.put(ModEntityCreator.DESERT_ENDERMAN.get(), DesertEndermanEntity.setAttributes());
        event.put(ModEntityCreator.DARK_OAK_ENDERMAN.get(), DarkOakEndermanEntity.setAttributes());
        event.put(ModEntityCreator.ICE_SPIKES_ENDERMAN.get(), IceSpikesEndermanEntity.setAttributes());
        event.put(ModEntityCreator.SWAMP_ENDERMAN.get(), SwampEndermanEntity.setAttributes());
        event.put(ModEntityCreator.WINDSWEPT_HILLS_ENDERMAN.get(), WindsweptHillsEndermanEntity.setAttributes());
        event.put(ModEntityCreator.WARPED_FOREST_ENDERMAN.get(), WarpedForestEndermanEntity.setAttributes());
        event.put(ModEntityCreator.CRIMSON_FOREST_ENDERMAN.get(), CrimsonForestEndermanEntity.setAttributes());
        event.put(ModEntityCreator.SOULSAND_VALLEY_ENDERMAN.get(), SoulsandValleyEndermanEntity.setAttributes());
        event.put(ModEntityCreator.NETHER_WASTES_ENDERMAN.get(), NetherWastesEndermanEntity.setAttributes());
        event.put(ModEntityCreator.CAVE_ENDERMAN.get(), CaveEndermanEntity.setAttributes());
        event.put(ModEntityCreator.FLOWER_FIELDS_ENDERMAN.get(), FlowerFieldsEndermanEntity.setAttributes());
        event.put(ModEntityCreator.MUSHROOM_ENDERMAN.get(), MushroomEndermanEntity.setAttributes());
        event.put(ModEntityCreator.END_ENDERMAN.get(), EndEndermanEntity.setAttributes());
        event.put(ModEntityCreator.OCEAN_ENDERMAN.get(), OceanEndermanEntity.setAttributes());
        event.put(ModEntityCreator.END_ISLANDS_ENDERMAN.get(), EndIslandsEndermanEntity.setAttributes());
    }

    // RENDERERS

    @SubscribeEvent
    public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityCreator.SAVANNA_ENDERMAN.get(), SavannaEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.BADLANDS_ENDERMAN.get(), BadlandsEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.SNOWY_ENDERMAN.get(), SnowyEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.SCARAB.get(), ScarabRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.SPIRIT.get(), SpiritRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.DESERT_ENDERMAN.get(), DesertEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.DARK_OAK_ENDERMAN.get(), DarkOakEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.ICE_SPIKES_ENDERMAN.get(), IceSpikesEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.SWAMP_ENDERMAN.get(), SwampEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.WINDSWEPT_HILLS_ENDERMAN.get(), WindsweptHillsEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.WARPED_FOREST_ENDERMAN.get(), WarpedForestEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.CRIMSON_FOREST_ENDERMAN.get(), CrimsonForestEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.SOULSAND_VALLEY_ENDERMAN.get(), SoulsandValleyEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.NETHER_WASTES_ENDERMAN.get(), NetherWastesEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.CAVE_ENDERMAN.get(), CaveEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.FLOWER_FIELDS_ENDERMAN.get(), FlowerFieldsEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.MUSHROOM_ENDERMAN.get(), MushroomEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.END_ENDERMAN.get(), EndEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.OCEAN_ENDERMAN.get(), OceanEndermanRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.END_ISLANDS_ENDERMAN.get(), EndIslandsEndermanRenderer::new);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
