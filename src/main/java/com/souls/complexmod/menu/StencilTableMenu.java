package com.souls.complexmod.menu;

import java.lang.StackWalker.Option;
import java.util.Optional;

import com.souls.complexmod.block.ModBlocks;
import com.souls.complexmod.block.entity.StencilTableBlockEntity;
import com.souls.complexmod.recipe.StencilShapedRecipe;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class StencilTableMenu extends AbstractContainerMenu {
    private final StencilTableBlockEntity blockEntity;
    private final Level level;
    private final ItemStackHandler items;
    private final Player player;

    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 3, 3);
    private final ResultContainer outputSlot = new ResultContainer();

    public StencilTableMenu(int containerId, Inventory pInv, FriendlyByteBuf buf) {
        this(containerId, pInv,
            (StencilTableBlockEntity) pInv.player.level().getBlockEntity(buf.readBlockPos()));
    }

    public StencilTableMenu(int id, Inventory pInv, StencilTableBlockEntity bEntity) {
        super(ModMenus.STENCIL_TABLE_MENU.get(), id);

        this.blockEntity = bEntity;
        this.level = blockEntity.getLevel();
        this.items = blockEntity.getItemHandler();
        this.player = pInv.player;

        addCraftingGrid();
        addPlayerInventory(pInv);
        addPlayerHotbar(pInv);
    }

    private void addCraftingGrid() {
        int index = 0;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                this.addSlot(new Slot(this.craftSlots, index,
                        30 + (column * 18),
                        17 + (row * 18)));
                index++;
            }
        }

        this.addSlot(new Slot(this.outputSlot, 9, 124, 35));
    }

    private void addPlayerHotbar(Inventory pInv) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(pInv, column, 8 + (column * 18), 142));
        }
    }
    
    private void addPlayerInventory(Inventory pInv) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(pInv, 9 + column + (row * 9), 8 + (column * 18), 84 + (row * 18)));
            }
        }
    }

    @Override
    public void slotsChanged(Container inventory) {
        super.slotsChanged(inventory);
        // Update the output slot based on the current input items
        updateCraftingResult();
    }

    //crafting logic
    public void updateCraftingResult() {
        outputSlot.setItem(0, ItemStack.EMPTY);

        Optional<StencilShapedRecipe> recipe = getCurrentRecipe();

        recipe.ifPresent(r -> {
            ItemStack result = r.getResultItem(level.registryAccess());
            outputSlot.setItem(0, result.copy());
        });
    }

    private boolean canCraft() {
        Optional<StencilShapedRecipe> recipe = getCurrentRecipe();
        return recipe.isPresent();
    }

    private Optional<StencilShapedRecipe> getCurrentRecipe() {
        return level.getRecipeManager()
                .getRecipeFor(StencilShapedRecipe.Type.INSTANCE, craftSlots, level);
    }

    private void craftItem() {
        Optional<StencilShapedRecipe> recipeOpt = getCurrentRecipe();
        if (recipeOpt.isEmpty()) {
            return;
        }

        StencilShapedRecipe recipe = recipeOpt.get();

        ItemStack resultItem = recipe.getResultItem(level.registryAccess());
        this.outputSlot.setItem(0, resultItem.copy());

        for (int i = 0; i < craftSlots.getContainerSize(); i++) {
            ItemStack slotStack = craftSlots.getItem(i);
            if (!slotStack.isEmpty()) {
                craftSlots.removeItem(i, 1);
            }
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 10;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
}
