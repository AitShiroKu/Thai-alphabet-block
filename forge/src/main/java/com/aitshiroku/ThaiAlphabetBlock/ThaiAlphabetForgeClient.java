package com.aitshiroku.ThaiAlphabetBlock;

import com.aitshiroku.thai_alphabet_block.ThaiAlphabetCommon;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import java.util.List;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.resources.Identifier;
import com.mojang.serialization.MapCodec;

@Mod.EventBusSubscriber(modid = ThaiAlphabetCommon.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ThaiAlphabetForgeClient {

    private ThaiAlphabetForgeClient() {
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Register custom item tint sources
            net.minecraft.client.color.item.ItemTintSources.ID_MAPPER.put(
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

            net.minecraft.client.color.item.ItemTintSources.ID_MAPPER.put(
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
        });
    }

    private static final BlockTintSource BACKGROUND_TINT_SOURCE = new BlockTintSource() {
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
    };

    private static final BlockTintSource GLYPH_TINT_SOURCE = new BlockTintSource() {
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
    };

    private static final List<BlockTintSource> THAI_BLOCK_TINT_SOURCES = List.of(
        BACKGROUND_TINT_SOURCE,
        GLYPH_TINT_SOURCE
    );

    public static void registerListeners() {
        RegisterColorHandlersEvent.Block.BUS.addListener(ThaiAlphabetForgeClient::registerBlockColors);
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (RegistryObject<Block> ro : ThaiAlphabetBlock.BLOCKS.getEntries()) {
            Block block = ro.get();
            if (!(block instanceof ThaiLetterBlock)) {
                continue;
            }
            event.register(THAI_BLOCK_TINT_SOURCES, block);
        }
    }
}
