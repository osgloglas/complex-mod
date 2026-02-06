package com.souls.complexmod.menu;

import com.souls.complexmod.block.entity.IronFurnaceBlockEntity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class IronFurnaceMenu extends AbstractContainerMenu {
    private final IronFurnaceBlockEntity blockEntity;
    private final Level level;
    private final ItemStackHandler items;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    public IronFurnaceMenu(int containerId, Inventory pInv, FriendlyByteBuf buf) {
        this(containerId, pInv, (IronFurnaceBlockEntity) pInv.player.level().getBlockEntity(buf.readBlockPos()),
            new SimpleContainerData(2));
    }

    public IronFurnaceMenu(int id, Inventory pInv, IronFurnaceBlockEntity bEntity, ContainerData extraData) {
        super(ModMenus.IRON_FURNACE_MENU.get(), id);

        this.blockEntity = bEntity;
        this.level = blockEntity.getLevel();
        this.items = blockEntity.getItemHandler();
        this.data = extraData;
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        addDataSlots(extraData);

        addFurnaceSlots();
        addPlayerInventory(pInv);
        addPlayerHotbar(pInv);
    }

    private void addFurnaceSlots() {
        this.addSlot(new SlotItemHandler(this.items, 0, 56, 17));
        this.addSlot(new SlotItemHandler(this.items, 1, 56, 53));

        this.addSlot(new SlotItemHandler(this.items, 9, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
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

    //burning getters
    public int getBurnTime() {
        return data.get(0);
    }

    public int getBurnTimeTotal() {
        return data.get(1);
    }

    //flame
    public boolean isBurning() {
        return data.get(0) > 0;
    }

    public int getBurnProgress(int scale) {
        int burn = data.get(0);
        int burnTotal = data.get(1);

        if (burnTotal == 0) {
            return 0;
        }
        return burn * scale / burnTotal;
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
