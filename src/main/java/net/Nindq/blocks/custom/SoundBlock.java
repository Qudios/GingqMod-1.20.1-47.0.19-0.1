package net.Nindq.blocks.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoundBlock extends Block {
    public SoundBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.playSound(pPlayer, pPos, SoundEvents.AZALEA_LEAVES_PLACE, SoundSource.BLOCKS, 1f, 1f);
        if (pLevel.isClientSide()){
            for (int i = pPos.getY(); i <= -64; i++){
                BlockState state = pLevel.getBlockState(pPos.below(i));
                Block stateBlock = state.getBlock();
                pPlayer.sendSystemMessage(Component.literal("Block " + I18n.get(stateBlock.getDescriptionId()) + " at "
                        + pPos.below(i).getY()));
                if (stateBlock != Blocks.AIR){
                    stateBlock.destroy(pLevel, pPos.below(i), state);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.literal("This makes Leaves Sounds when u click am"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
