package edu.depaul.rogue.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
    private Mace mace;
    private Longsword longsword;
    private Dagger dagger;
    private TwoSword twoSword;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        mace = new Mace();
        longsword = new Longsword();
        dagger = new Dagger();
        twoSword = new TwoSword();

        inventory = new Inventory(5);
    }

    @Test
    void addInventory() {
        inventory.addItem(mace);
        inventory.addItem(longsword);
        inventory.addItem(dagger);
        inventory.addItem(twoSword);

        assertTrue(inventory.getItems().contains(mace));
        assertTrue(inventory.getItems().contains(longsword));
        assertTrue(inventory.getItems().contains(dagger));
        assertTrue(inventory.getItems().contains(twoSword));

        System.out.println(inventory.getItems());

        assertEquals(4, inventory.getSize());
    }

    @Test
    void removeInventory() {
        inventory.addItem(mace);
        inventory.addItem(longsword);
        inventory.addItem(dagger);
        inventory.addItem(twoSword);

        System.out.println("index 2: " + inventory.get(2).getName());
        System.out.println("items before removal: " + inventory.getItems());

        inventory.removeItem(mace);
        inventory.removeItem(longsword);
        inventory.removeItem(dagger);
        inventory.removeItem(twoSword);

        assertEquals(0, inventory.getSize());
    }

    @Test
    void maceTest() {
        assertTrue(mace.getDamage() > 0);
    }

    @Test
    void longswordTest() {
        assertTrue(longsword.getDamage() > 0);
    }

    @Test
    void daggerTest() {
        assertTrue(dagger.getDamage() > 0);
    }

    @Test
    void twoSwordTest() {
        assertTrue(twoSword.getDamage() > 0);
    }

}