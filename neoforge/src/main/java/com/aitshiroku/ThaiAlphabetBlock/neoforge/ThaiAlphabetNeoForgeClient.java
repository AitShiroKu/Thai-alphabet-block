package com.aitshiroku.ThaiAlphabetBlock.neoforge;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import java.util.List;
import net.minecraft.resources.Identifier;
import com.mojang.serialization.MapCodec;

public final class ThaiAlphabetNeoForgeClient {

    private ThaiAlphabetNeoForgeClient() {
    }

    public static void registerListeners(IEventBus modEventBus) {
        modEventBus.addListener(ThaiAlphabetNeoForgeClient::onClientSetup);
        modEventBus.addListener(ThaiAlphabetNeoForgeClient::registerItemTintSources);
        modEventBus.addListener(ThaiAlphabetNeoForgeClient::registerBlockColors);
    }

    @SuppressWarnings("deprecation")
    public static void onClientSetup(net.neoforged.fml.event.lifecycle.FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Render type setting is now handled automatically
        });
    }

    public static void registerItemTintSources(RegisterColorHandlersEvent.ItemTintSources event) {
        event.register(
                Identifier.fromNamespaceAndPath("thai_alphabet_block", "background_tint"),
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

        event.register(
                Identifier.fromNamespaceAndPath("thai_alphabet_block", "glyph_tint"),
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
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        for (net.neoforged.neoforge.registries.DeferredHolder<Block, ? extends Block> ro : ThaiAlphabetBlockNeoForge.BLOCKS
                .getEntries()) {
            Block block = ro.get();
            if (!(block instanceof ThaiLetterBlock)) {
                continue;
            }
            List<BlockTintSource> tintSources = List.of(
                new BlockTintSource() {
                    @Override
                    public int color(BlockState state) {
                        ThaiAlphabetColorProperties.ThaiBlockColor color = state.hasProperty(ThaiLetterBlock.COLOR)
                                ? state.getValue(ThaiLetterBlock.COLOR)
                                : ThaiAlphabetColorProperties.ThaiBlockColor.NONE;
                        return ThaiAlphabetColorUtil.backgroundArgbFromColor(color);
                    }

                    @Override
                    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                        ThaiAlphabetColorProperties.ThaiBlockColor color = state.hasProperty(ThaiLetterBlock.COLOR)
                                ? state.getValue(ThaiLetterBlock.COLOR)
                                : ThaiAlphabetColorProperties.ThaiBlockColor.NONE;
                        return ThaiAlphabetColorUtil.backgroundArgbFromColor(color);
                    }
                },
                new BlockTintSource() {
                    @Override
                    public int color(BlockState state) {
                        DyeColor glyphDye = state.hasProperty(ThaiLetterBlock.GLYPH_COLOR)
                                ? state.getValue(ThaiLetterBlock.GLYPH_COLOR)
                                : DyeColor.BLACK;
                        return ThaiAlphabetColorUtil.glyphArgbFromDye(glyphDye);
                    }

                    @Override
                    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                        DyeColor glyphDye = state.hasProperty(ThaiLetterBlock.GLYPH_COLOR)
                                ? state.getValue(ThaiLetterBlock.GLYPH_COLOR)
                                : DyeColor.BLACK;
                        return ThaiAlphabetColorUtil.glyphArgbFromDye(glyphDye);
                    }
                }
            );
            event.register(tintSources, block);
        }
    }
}
