package online.magicksaddon.magicsaddonmod.entity;

import static net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES;

import java.util.function.BiFunction;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import online.kingdomkeys.kingdomkeys.client.render.magic.InvisibleEntityRenderer;
import online.magicksaddon.magicsaddonmod.MagicksAddonMod;
import online.magicksaddon.magicsaddonmod.client.model.*;
import online.magicksaddon.magicsaddonmod.client.render.*;
import online.magicksaddon.magicsaddonmod.client.render.shotlock.BioShotEntityRenderer;
import online.magicksaddon.magicsaddonmod.entity.magic.*;
import online.magicksaddon.magicsaddonmod.entity.shotlock.*;

public class ModEntitiesMA {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ENTITY_TYPES, MagicksAddonMod.MODID);

    public static final RegistryObject<EntityType<HolyEntity>> TYPE_HOLY = createEntityType(HolyEntity::new, HolyEntity::new, MobCategory.MISC,"entity_holy", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<RuinEntity>> TYPE_RUIN = createEntityType(RuinEntity::new, RuinEntity::new, MobCategory.MISC,"entity_ruin", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<BalloonEntity>> TYPE_BALLOON = createEntityType(BalloonEntity::new, BalloonEntity::new, MobCategory.MISC, "entity_balloon", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<BalloongaEntity>> TYPE_BALLOONGA = createEntityType(BalloongaEntity::new, BalloongaEntity::new, MobCategory.MISC, "entity_balloonga", 1F, 1F);
    public static final RegistryObject<EntityType<UltimaEntity>> TYPE_ULTIMA = createEntityType(UltimaEntity::new, UltimaEntity::new, MobCategory.MISC, "entity_ultima", 1F, 1F);
    public static final RegistryObject<EntityType<CometEntity>> TYPE_COMET = createEntityType(CometEntity::new, CometEntity::new, MobCategory.MISC, "entity_comet", 2F, 2F);

    public static final RegistryObject<EntityType<BioBarrageShotEntity>> TYPE_BIO_SHOT = createEntityType(BioBarrageShotEntity::new, BioBarrageShotEntity::new, MobCategory.MISC, "entity_bio_shot", 0.5F, 0.5F);


    public static final RegistryObject<EntityType<FlameSalvoCoreEntity>> TYPE_SHOTLOCK_FLAME_SALVO = createEntityType(FlameSalvoCoreEntity::new, FlameSalvoCoreEntity::new, MobCategory.MISC, "entity_shotlock_flame_salvo_core", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<BubbleBlasterCoreEntity>> TYPE_SHOTLOCK_BUBBLE_BLASTER = createEntityType(BubbleBlasterCoreEntity::new, BubbleBlasterCoreEntity::new, MobCategory.MISC, "entity_shotlock_bubble_blaster_core", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<ThunderstormCoreEntity>> TYPE_SHOTLOCK_THUNDERSTORM = createEntityType(ThunderstormCoreEntity::new, ThunderstormCoreEntity::new, MobCategory.MISC, "entity_shotlock_thunderstorm_core", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<BioBarrageCoreEntity>> TYPE_SHOTLOCK_BIO_BARRAGE = createEntityType(BioBarrageCoreEntity::new, BioBarrageCoreEntity::new, MobCategory.MISC, "entity_shotlock_bio_barrage_core", 0.5F, 0.5F);
    public static final RegistryObject<EntityType<MeteorShowerCoreEntity>> TYPE_SHOTLOCK_METEOR_SHOWER = createEntityType(MeteorShowerCoreEntity::new, MeteorShowerCoreEntity::new, MobCategory.MISC, "entity_shotlock_meteor_shower_core", 0.5F, 0.5F);


    public static <T extends Entity, M extends EntityType<T>>RegistryObject<EntityType<T>> createEntityType(EntityType.EntityFactory<T> factory, BiFunction<PlayMessages.SpawnEntity, Level, T> clientFactory, MobCategory classification, String name, float sizeX, float sizeY) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(factory, classification)
                .setCustomClientFactory(clientFactory)
                .setShouldReceiveVelocityUpdates(false)
                .setUpdateInterval(1)
                .setTrackingRange(8)
                .sized(sizeX, sizeY)
                .build(name));
    }
    @OnlyIn(Dist.CLIENT)
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HolyModel.LAYER_LOCATION, HolyModel::createBodyLayer);
        event.registerLayerDefinition(RuinModel.LAYER_LOCATION, RuinModel::createBodyLayer);
        event.registerLayerDefinition(BalloonModel.LAYER_LOCATION, BalloonModel::createBodyLayer);
        event.registerLayerDefinition(BalloongaModel.LAYER_LOCATION, BalloongaModel::createBodyLayer);
        event.registerLayerDefinition(UltimaModel.LAYER_LOCATION, UltimaModel::createBodyLayer);
        event.registerLayerDefinition(CometModel.LAYER_LOCATION, CometModel::createBodyLayer);

        event.registerLayerDefinition(BerserkAuraModel.LAYER_LOCATION, BerserkAuraModel::createBodyLayer);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(TYPE_HOLY.get(), HolyEntityRenderer::new);
        event.registerEntityRenderer(TYPE_RUIN.get(), RuinEntityRenderer::new);
        event.registerEntityRenderer(TYPE_BALLOON.get(), BalloonEntityRenderer::new);
        event.registerEntityRenderer(TYPE_BALLOONGA.get(), BalloongaEntityRenderer::new);
        event.registerEntityRenderer(TYPE_COMET.get(), CometEntityRenderer::new);
        event.registerEntityRenderer(TYPE_ULTIMA.get(), UltimaEntityRenderer::new);

        event.registerEntityRenderer(TYPE_BIO_SHOT.get(), BioShotEntityRenderer::new);

        event.registerEntityRenderer(TYPE_SHOTLOCK_FLAME_SALVO.get(), InvisibleEntityRenderer::new);
        event.registerEntityRenderer(TYPE_SHOTLOCK_BUBBLE_BLASTER.get(), InvisibleEntityRenderer::new);

        event.registerEntityRenderer(TYPE_SHOTLOCK_BIO_BARRAGE.get(), InvisibleEntityRenderer::new);
        event.registerEntityRenderer(TYPE_SHOTLOCK_THUNDERSTORM.get(), InvisibleEntityRenderer::new);
        event.registerEntityRenderer(TYPE_SHOTLOCK_METEOR_SHOWER.get(), InvisibleEntityRenderer::new);


    }


}
