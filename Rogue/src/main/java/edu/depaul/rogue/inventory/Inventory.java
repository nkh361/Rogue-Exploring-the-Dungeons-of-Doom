package edu.depaul.rogue.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private int maxCapacity;

    public Inventory(int maxCapacity) {
        items = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    public void addItem(Item item) {
        if(items.size() < maxCapacity) {
            items.add(item);
            System.out.println("Added item: " + item.getName());
        }
        else {
            System.out.println("Items exceeds max capacity: " + maxCapacity);
        }
    }

    public void removeItem(Item item) {
        if (items.remove(item)) {
            System.out.println("Removed item: " + item.getName());
        }
        else {
            System.out.println("Item not found: " + item.getName());
        }
    }

    public List<Item> getItems() {
        return items;
    }
}
