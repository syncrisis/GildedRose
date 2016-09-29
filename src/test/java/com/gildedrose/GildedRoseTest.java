package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class GildedRoseTest {

    private GildedRose getGildedRoseFromItem(String itemName, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(itemName, sellIn, quality) };
        return new GildedRose(items);
    }

    @Test
    public void updateQualityBasicTest() {
        GildedRose app = getGildedRoseFromItem("foo", 10, 20);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(19, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    public void qualityDegradesTwiceAsFast() {
        Item[] items = new Item[] { new Item("foo", -1, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    public void qualityDegradesTwiceAsFastAt0() {
        GildedRose app = getGildedRoseFromItem("foo", 0, 20);
        app.updateQuality();
        assertEquals(18, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    public void qualityDegradesFastAt0()
    {
        GildedRose app = getGildedRoseFromItem("foo", 1, 20);
        app.updateQuality();
        app.updateQuality();
        assertEquals(17, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    public void qualityNeverNegative() {
        GildedRose app = getGildedRoseFromItem("foo", 10, 0);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void agedBrieIncreasesQualityByOne() {
        GildedRose app = getGildedRoseFromItem("Aged Brie", 10, 0);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    @Test
    public void agedBrieIncreasesQualityByTwoBeyondSellIn() {
        GildedRose app = getGildedRoseFromItem("Aged Brie", -1, 0);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }

    @Test
    public void qualityNonSulfurasNeverAbove50() {
        GildedRose app = getGildedRoseFromItem("Aged Brie", -1, 50);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void sulfurasQualityNeverChanges() {
        GildedRose app = getGildedRoseFromItem("Sulfuras, Hand of Ragnaros", 2, 80);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void sulfurasSellInNeverChanges() {
        GildedRose app = getGildedRoseFromItem("Sulfuras, Hand of Ragnaros", 2, 80);
        app.updateQuality();
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    public void backstagePassesQualityIncrease() {
        GildedRose app = getGildedRoseFromItem("Backstage passes to a TAFKAL80ETC concert", 20, 10);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }

    @Test
    public void backstagePassesQualityIncreaseByTwo() {
        GildedRose app = getGildedRoseFromItem("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }

    @Test
    public void backstagePassesQualityIncreaseByThree() {
        GildedRose app = getGildedRoseFromItem("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }

    @Test
    public void backstagePassesQualityGoesToZero() {
        GildedRose app = getGildedRoseFromItem("Backstage passes to a TAFKAL80ETC concert", 0, 8);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void sulfurasKeepsInitialValue() {
        GildedRose app = getGildedRoseFromItem("Sulfuras, Hand of Ragnaros", 2, 50);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void itemToStringCorrect() {
        GildedRose app = getGildedRoseFromItem("foo", 2, 50);
        String result = app.items[0].toString();

        assertEquals("foo, 2, 50", result);
    }

    @Test
    public void conjuredDegradesTwiceAsFastAsNormalItemsBeforeSellin() {
        GildedRose app = getGildedRoseFromItem("Conjured", 10, 10);
        app.updateQuality();

        assertEquals(8, app.items[0].quality);
    }

    @Test
    public void conjuredDegradesTwiceAsFastAsNormalItemsAfterSellin() {
        GildedRose app = getGildedRoseFromItem("Conjured", 0, 10);
        app.updateQuality();

        assertEquals(6, app.items[0].quality);
    }
}
