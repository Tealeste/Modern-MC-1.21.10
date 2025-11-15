/*
 * Modern UI.
 * Copyright (C) 2019-2022 BloCamLimb. All rights reserved.
 *
 * Modern UI is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Modern UI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Modern UI. If not, see <https://www.gnu.org/licenses/>.
 */

package icyllis.modernui.mc.text.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import icyllis.modernui.mc.text.TextLayoutEngine;
import icyllis.modernui.mc.text.TextRenderType;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Handle deferred rendering and transparency sorting (painter's algorithm).
 */
@Mixin(LevelRenderer.class)
public class MixinLevelRenderer {

    @Shadow
    @Final
    private RenderBuffers renderBuffers;

    @Inject(method = "renderLevel(Lcom/mojang/blaze3d/resource/GraphicsResourceAllocator;Lnet/minecraft/client/DeltaTracker;ZLnet/minecraft/client/Camera;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lorg/joml/Vector4f;Z)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/LevelRenderer;addMainPass(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;Lnet/minecraft/client/renderer/culling/Frustum;Lorg/joml/Matrix4f;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;ZLnet/minecraft/client/renderer/state/LevelRenderState;Lnet/minecraft/client/DeltaTracker;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
                    shift = At.Shift.AFTER))
    private void endTextBatchAfterMainPass(GraphicsResourceAllocator graphicsResourceAllocator, DeltaTracker deltaTracker,
                                           boolean renderBlockOutline, Camera camera, Matrix4f poseStack, Matrix4f projection,
                                           Matrix4f invertedProjection, GpuBufferSlice fogSlice, Vector4f vector4f, boolean bl,
                                           CallbackInfo ci) {
        flushQueuedText();
    }

    private void flushQueuedText() {
        if (TextLayoutEngine.sUseTextShadersInWorld) {
            RenderType firstSDFFillType = TextRenderType.getFirstSDFFillType();
            RenderType firstSDFStrokeType = TextRenderType.getFirstSDFStrokeType();
            if (firstSDFFillType != null) {
                renderBuffers.bufferSource().endBatch(firstSDFFillType);
            }
            if (firstSDFStrokeType != null) {
                renderBuffers.bufferSource().endBatch(firstSDFStrokeType);
            }
        }
    }
}
