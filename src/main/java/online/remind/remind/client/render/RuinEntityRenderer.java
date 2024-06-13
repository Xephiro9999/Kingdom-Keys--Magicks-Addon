package online.remind.remind.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import online.remind.remind.KingdomKeysReMind;
import online.remind.remind.client.model.RuinModel;

import javax.annotation.Nullable;

public class RuinEntityRenderer extends EntityRenderer<ThrowableProjectile> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(KingdomKeysReMind.MODID,"textures/entity/models/ruin.png");
    RuinModel<Entity> ruinModel;

    public RuinEntityRenderer(EntityRendererProvider.Context context){
        super(context);
        this.shadowRadius = 0.25F;
        ruinModel = new RuinModel<>(context.bakeLayer(RuinModel.LAYER_LOCATION));
    }

    float ticks = 0F;
    float prevRotationTicks = 0F;

    @Override
    public void render(ThrowableProjectile entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        {
            VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
            matrixStackIn.scale(2, 2, 2);
            this.ruinModel.renderToBuffer(matrixStackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1,1,1,1);
        }

        matrixStackIn.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(ThrowableProjectile entity) {
        return TEXTURE;
    }
}
