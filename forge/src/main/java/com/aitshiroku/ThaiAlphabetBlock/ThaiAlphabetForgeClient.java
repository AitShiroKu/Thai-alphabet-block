package com.aitshiroku.ThaiAlphabetBlock;

import com.aitshiroku.thai_alphabet_block.ThaiAlphabetCommon;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.resources.ResourceLocation;
import com.mojang.serialization.MapCodec;

@Mod.EventBusSubscriber(modid = ThaiAlphabetCommon.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ThaiAlphabetForgeClient {

    private ThaiAlphabetForgeClient() {
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (RegistryObject<Block> ro : ThaiAlphabetBlock.BLOCKS.getEntries()) {
                ItemBlockRenderTypes.setRenderLayer(ro.get(), RenderType.cutout());
            }

            // Register custom item tint sources
            net.minecraft.client.color.item.ItemTintSources.ID_MAPPER.put(
                    ResourceLocation.fromNamespaceAndPath("thai_alphabet_block", "background_tint"),
                    MapCodec.unit(new net.minecraft.client.color.item.ItemTintSource() {
                        @Override
                        public int calculate(ItemStack stack, net.minecraft.client.multiplayer.ClientLevel level,
                                net.minecraft.world.entity.LivingEntity entity) {
                            if (stack.getItem() instanceof BlockItem blockItem) {
                                Block block = blockItem.getBlock();
                                BlockState state = ThaiAlphabetBlockStateUtil.stateFromItemStack(stack, block);
                                if (state.hasProperty(ThaiLetterBlock.COLOR)) {
                                    ThaiAlphabetColorProperties.ThaiBlockColor color = state
                                            .getValue(ThaiLetterBlock.COLOR);
                                    return ThaiAlphabetColorUtil.backgroundArgbFromColor(color);
                                }
                            }
                            return ThaiAlphabetColorUtil
                                    .backgroundArgbFromColor(ThaiAlphabetColorProperties.ThaiBlockColor.NONE);
                        }

                        @Override
                        public MapCodec<? extends net.minecraft.client.color.item.ItemTintSource> type() {
                            return MapCodec.unit(this);
                        }
                    }));

            net.minecraft.client.color.item.ItemTintSources.ID_MAPPER.put(
                    ResourceLocation.fromNamespaceAndPath("thai_alphabet_block", "glyph_tint"),
                    MapCodec.unit(new net.minecraft.client.color.item.ItemTintSource() {
                        @Override
                        public int calculate(ItemStack stack, net.minecraft.client.multiplayer.ClientLevel level,
                                net.minecraft.world.entity.LivingEntity entity) {
                            if (stack.getItem() instanceof BlockItem blockItem) {
                                Block block = blockItem.getBlock();
                                BlockState state = ThaiAlphabetBlockStateUtil.stateFromItemStack(stack, block);
                                if (state.hasProperty(ThaiLetterBlock.GLYPH_COLOR)) {
                                    DyeColor glyphDye = state.getValue(ThaiLetterBlock.GLYPH_COLOR);
                                    return ThaiAlphabetColorUtil.glyphArgbFromDye(glyphDye);
                                }
                            }
                            return ThaiAlphabetColorUtil.glyphArgbFromDye(DyeColor.BLACK);
                        }

                        @Override
                        public MapCodec<? extends net.minecraft.client.color.item.ItemTintSource> type() {
                            return MapCodec.unit(this);
                        }
                    }));
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (RegistryObject<Block> ro : ThaiAlphabetBlock.BLOCKS.getEntries()) {
            Block block = ro.get();
            if (!(block instanceof ThaiLetterBlock)) {
                continue;
            }
            event.register(
                    (state, level, pos, tintIndex) -> {
                        if (tintIndex != 0 && tintIndex != 1) {
                            return -1;
                        }
                        if (tintIndex == 0) {
                            // Background color
                            ThaiAlphabetColorProperties.ThaiBlockColor color = state.hasProperty(ThaiLetterBlock.COLOR)
                                    ? state.getValue(ThaiLetterBlock.COLOR)
                                    : ThaiAlphabetColorProperties.ThaiBlockColor.NONE;
                            return ThaiAlphabetColorUtil.backgroundArgbFromColor(color);
                        } else {
                            // Glyph color
                            DyeColor glyphDye = state.hasProperty(ThaiLetterBlock.GLYPH_COLOR)
                                    ? state.getValue(ThaiLetterBlock.GLYPH_COLOR)
                                    : DyeColor.BLACK;
                            return ThaiAlphabetColorUtil.glyphArgbFromDye(glyphDye);
                        }
                    },
                    block);
        }
    }
}
