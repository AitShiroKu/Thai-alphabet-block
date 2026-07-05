package com.aitshiroku.ThaiAlphabetBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ThaiLetterBlock extends Block {

    public static final EnumProperty<ThaiAlphabetColorProperties.ThaiBlockColor> COLOR = ThaiAlphabetColorProperties.COLOR;
    public static final EnumProperty<DyeColor> GLYPH_COLOR = ThaiAlphabetColorProperties.GLYPH_COLOR;

    public ThaiLetterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(COLOR, ThaiAlphabetColorProperties.ThaiBlockColor.NONE)
                .setValue(GLYPH_COLOR, DyeColor.BLACK));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COLOR, GLYPH_COLOR);
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit) {
        if (stack.getItem() instanceof DyeItem dyeItem) {
            // NOTE: Shift + Right-click (glyph color) is handled by
            // ThaiAlphabetGlyphDyeHandler, because vanilla skips useItemOn() when sneaking.
            DyeColor dyeColor = stack.get(net.minecraft.core.component.DataComponents.DYE);
            if (dyeColor == null) {
                return InteractionResult.PASS;
            }
            ThaiAlphabetColorProperties.ThaiBlockColor newColor = ThaiAlphabetColorProperties.ThaiBlockColor.fromDyeColor(dyeColor);
            if (state.getValue(COLOR) == newColor) {
                return InteractionResult.CONSUME;
            }
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(COLOR, newColor), 3);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                level.playSound(null, pos, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}
