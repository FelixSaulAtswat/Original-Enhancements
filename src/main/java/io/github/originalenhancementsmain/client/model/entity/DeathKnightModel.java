package io.github.originalenhancementsmain.client.model.entity;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.originalenhancementsmain.OriginalEnhancementsMain;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class DeathKnightModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OriginalEnhancementsMain.MOD_ID, "death_knight_model"), "originalenhancement");
	private final ModelPart leftleg;
	private final ModelPart leftarm;
	private final ModelPart rightarm;
	private final ModelPart body;
	private final ModelPart rightleg;
	private final ModelPart head;

	public DeathKnightModel(ModelPart root) {
		this.leftleg = root.getChild("leftleg");
		this.leftarm = root.getChild("leftarm");
		this.rightarm = root.getChild("rightarm");
		this.body = root.getChild("body");
		this.rightleg = root.getChild("rightleg");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -1.525F, -1.625F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 32).addBox(-2.0F, -1.525F, -1.625F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.05F))
		.texOffs(16, 48).addBox(-2.0F, 6.475F, -3.625F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.06F))
		.texOffs(48, 32).addBox(-2.0F, 8.575F, -3.625F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.06F)), PartPose.offset(-3.0F, 13.525F, 0.625F));

		PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.2F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 66).addBox(-2.0F, -0.15F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.05F))
		.texOffs(0, 48).addBox(-2.0F, -1.15F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(5.0F, 0.95F, 1.0F));

		PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.2F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 66).addBox(-2.0F, -0.15F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.05F))
		.texOffs(0, 32).addBox(-2.0F, -1.15F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-7.0F, 0.95F, 1.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 48).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.0F, 6.0F, 1.0F));

		PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(40, 17).addBox(-2.5F, -1.3F, -0.375F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 32).addBox(-2.5F, -1.3F, -0.375F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.05F))
		.texOffs(16, 48).addBox(-2.5F, 6.7F, -2.375F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.06F))
		.texOffs(48, 32).addBox(-2.5F, 8.8F, -2.375F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.06F)), PartPose.offset(1.5F, 13.3F, -0.625F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.0F, -4.0F, 0.5F));

		PartDefinition ci = head.addOrReplaceChild("ci", CubeListBuilder.create().texOffs(21, 85).addBox(-7.5F, -26.0F, -8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(49, 96).addBox(-7.5F, -21.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offset(7.0F, 16.0F, 3.5F));

		PartDefinition ci7 = head.addOrReplaceChild("ci7", CubeListBuilder.create().texOffs(21, 85).addBox(-7.5F, -26.0F, -8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(49, 96).addBox(-7.5F, -21.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offset(10.0F, 16.0F, 10.5F));

		PartDefinition ci6 = head.addOrReplaceChild("ci6", CubeListBuilder.create().texOffs(21, 85).addBox(-7.5F, -26.0F, -8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(49, 96).addBox(-7.5F, -21.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offset(4.0F, 16.0F, 10.5F));

		PartDefinition ci5 = head.addOrReplaceChild("ci5", CubeListBuilder.create().texOffs(21, 85).addBox(-7.5F, -26.0F, -7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(49, 96).addBox(-7.5F, -21.0F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offset(11.0F, 16.0F, 6.5F));

		PartDefinition ci4 = head.addOrReplaceChild("ci4", CubeListBuilder.create().texOffs(21, 85).addBox(-7.5F, -26.0F, -7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(35, 90).addBox(-7.5F, -21.0F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offset(3.0F, 16.0F, 6.5F));

		PartDefinition ci3 = head.addOrReplaceChild("ci3", CubeListBuilder.create().texOffs(21, 85).addBox(-7.5F, -26.0F, -8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(49, 96).addBox(-7.5F, -21.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offset(10.0F, 16.0F, 4.5F));

		PartDefinition ci2 = head.addOrReplaceChild("ci2", CubeListBuilder.create().texOffs(21, 85).addBox(-7.5F, -26.0F, -8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(49, 96).addBox(-7.5F, -21.0F, -8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offset(4.0F, 16.0F, 4.5F));

		PartDefinition dizuo = head.addOrReplaceChild("dizuo", CubeListBuilder.create().texOffs(56, 16).addBox(-22.5F, -10.0F, -20.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.5F)), PartPose.offset(21.0F, 3.0F, 20.0F));

		PartDefinition daci = dizuo.addOrReplaceChild("daci", CubeListBuilder.create().texOffs(34, 77).addBox(0.0F, -19.0F, -15.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-22.0F, -1.0F, -4.0F));

		PartDefinition zs = head.addOrReplaceChild("zs", CubeListBuilder.create().texOffs(64, 0).addBox(-7.0F, 8.0F, 23.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(3.0F, -4.0F, -27.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * 0.007453292F;
		this.head.xRot = headPitch * 0.007453292F;
		this.leftarm.xRot = Mth.cos(limbSwing * 0.7662F) * 1.3F * limbSwingAmount;
		this.rightarm.xRot = Mth.cos(limbSwing * 0.7662F + (float) Math.PI) * 1.3F * limbSwingAmount;
		this.leftleg.xRot = Mth.cos(limbSwing * 0.5662F) * 1.4F * limbSwingAmount;
		this.rightleg.xRot = Mth.cos(limbSwing * 0.5662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leftleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}