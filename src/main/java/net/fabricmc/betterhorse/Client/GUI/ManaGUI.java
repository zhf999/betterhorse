package net.fabricmc.betterhorse.Client.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.betterhorse.BetterHorse;
import net.fabricmc.betterhorse.Entity.CommonHorseEntity;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import static net.fabricmc.betterhorse.Entity.AttributesRegistryHandler.HORSE_MAX_MANA;

@Environment(EnvType.CLIENT)
public class ManaGUI extends DrawableHelper implements HudRenderCallback {
    private static final Identifier EMPTY_MANA = new Identifier(BetterHorse.MODID,"textures/gui/mana.png");
    public ManaGUI(){

    }



    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        Entity player = MinecraftClient.getInstance().getCameraEntity();
        if (player instanceof  PlayerEntity)
        {
            Entity horse = ((PlayerEntity)player).getVehicle();
            if(horse instanceof CommonHorseEntity)
            {
                float mana = ((CommonHorseEntity) horse).getMana();
                float max_mana = (float) ((CommonHorseEntity)horse).getAttributeValue(HORSE_MAX_MANA);
                MinecraftClient.getInstance().getTextureManager().bindTexture(EMPTY_MANA);
                drawTexture(matrixStack,20,60,4,3,151,13);
                drawTexture(matrixStack,20,60,4,18,(int)(151 * (mana)/max_mana),13);
                drawStringWithShadow(matrixStack,MinecraftClient.getInstance().textRenderer,(int)mana+"/"+max_mana,70,62,0xffffffff);
                int posX = 45, posY = 130;
                drawEntity(posX+45, posY+5,15, (LivingEntity) horse);
            }
        }
    }

    public static void drawEntity(int x, int y, int size, LivingEntity entity) {
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)x, (float)y, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.translate(0.0D, 0.0D, 1000.0D);
        matrixStack.scale((float)size, (float)size, (float)size);
        Quaternion quaternion = Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F);
        //
        Quaternion quaternion2 = Vec3f.POSITIVE_X.getDegreesQuaternion( -25.0F);
        quaternion.hamiltonProduct(quaternion2);
        matrixStack.multiply(quaternion);
        float h = entity.bodyYaw;
        float i = entity.yaw;
        float j = entity.pitch;
        float k = entity.prevHeadYaw;
        float l = entity.headYaw;

        entity.pitch = -60.0F;
        entity.headYaw = entity.yaw;
        entity.prevHeadYaw = entity.yaw;
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        quaternion2.conjugate();
        entityRenderDispatcher.setRotation(quaternion2);
        entityRenderDispatcher.setRenderShadows(false);
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        RenderSystem.runAsFancy(() -> {
            entityRenderDispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixStack, immediate, 15728880);
        });
        immediate.draw();
        entityRenderDispatcher.setRenderShadows(true);
        entity.bodyYaw = h;
        entity.yaw = i;
        entity.pitch = j;
        entity.prevHeadYaw = k;
        entity.headYaw = l;
        RenderSystem.popMatrix();
    }

}
