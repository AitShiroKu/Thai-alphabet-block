package com.aitshiroku.ThaiAlphabetBlock;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.color.item.ItemTintSource;
import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;

public final class ThaiAlphabetFabricClient implements ClientModInitializer {

    private static final BlockTintSource BACKGROUND_TINT_SOURCE = new BlockTintSource() {
        @Override
        public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
            ThaiAlphabetColorProperties.ThaiBlockColor color = state.hasProperty(ThaiLetterBlock.COLOR)
                    ? state.getValue(ThaiLetterBlock.COLOR)
                    : ThaiAlphabetColorProperties.ThaiBlockColor.NONE;
            return ThaiAlphabetColorUtil.backgroundArgbFromColor(color);
        }

        @Override
        public int color(BlockState state) {
            ThaiAlphabetColorProperties.ThaiBlockColor color = state.hasProperty(ThaiLetterBlock.COLOR)
                    ? state.getValue(ThaiLetterBlock.COLOR)
                    : ThaiAlphabetColorProperties.ThaiBlockColor.NONE;
            return ThaiAlphabetColorUtil.backgroundArgbFromColor(color);
        }
    };

    private static final BlockTintSource GLYPH_TINT_SOURCE = new BlockTintSource() {
        @Override
        public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
            DyeColor glyphDye = state.hasProperty(ThaiLetterBlock.GLYPH_COLOR)
                    ? state.getValue(ThaiLetterBlock.GLYPH_COLOR)
                    : DyeColor.BLACK;
            return ThaiAlphabetColorUtil.glyphArgbFromDye(glyphDye);
        }

        @Override
        public int color(BlockState state) {
            DyeColor glyphDye = state.hasProperty(ThaiLetterBlock.GLYPH_COLOR)
                    ? state.getValue(ThaiLetterBlock.GLYPH_COLOR)
                    : DyeColor.BLACK;
            return ThaiAlphabetColorUtil.glyphArgbFromDye(glyphDye);
        }
    };

    private static final java.util.List<BlockTintSource> THAI_BLOCK_TINT_SOURCES = java.util.List.of(
        BACKGROUND_TINT_SOURCE,
        GLYPH_TINT_SOURCE
    );

    @Override
    public void onInitializeClient() {
        // Register custom item tint sources
        ItemTintSources.ID_MAPPER.put(
                Identifier.fromNamespaceAndPath("thai_alphabet_block", "background_tint"),
                MapCodec.unit(new ItemTintSource() {
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
                    public MapCodec<? extends ItemTintSource> type() {
                        return MapCodec.unit(this);
                    }
                }));

        ItemTintSources.ID_MAPPER.put(
                Identifier.fromNamespaceAndPath("thai_alphabet_block", "glyph_tint"),
                MapCodec.unit(new ItemTintSource() {
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
                    public MapCodec<? extends ItemTintSource> type() {
                        return MapCodec.unit(this);
                    }
                }));

        for (Block block : ThaiAlphabetBlockFabric.letterBlocksView()) {
            if (!(block instanceof ThaiLetterBlock)) {
                continue;
            }
            BlockColorRegistry.register(THAI_BLOCK_TINT_SOURCES, block);
        }
    }
}
