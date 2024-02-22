package net.Nindq.items.custom;

import net.Nindq.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OreDetecterItem extends Item {
    public OreDetecterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if (!pContext.getLevel().isClientSide){
            BlockPos positonClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positonClicked.getY() + 64; i++){
                BlockState state = pContext.getLevel().getBlockState(positonClicked.below(i));
                System.out.println(i);

                if (isValuableBlock(state)){
                    outputValuableCoordinates(positonClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }
            if (!foundBlock){
                player.sendSystemMessage(Component.literal("No Cool Ores Found Boi"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.gingqmod.oredectectoritem.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoordinates(BlockPos blockpos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockpos.getX() + "," + blockpos.getY() + "," + blockpos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.ORE_DETECTOR_BLOCKS);
    }
}
