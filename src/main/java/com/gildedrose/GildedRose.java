package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured";
    private static final int MAX_QUALITY = 50;
    private static final int BACKSTAGE_DOUBLE_SELLIN = 11;
    private static final int BACKSTAGE_TRIPLE_SELLIN = 6;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    void updateQuality() {

        for (Item item : items) {

            int qualityChange = 0;

            if (!item.name.equals(SULFURAS)) {

                item.sellIn--;

                if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.name.equals(CONJURED)) {
                        qualityChange = getNormalQualityDelta(item) * 2;
                    } else {
                        qualityChange = getNormalQualityDelta(item);
                    }
                } else if (item.name.equals(AGED_BRIE)) {
                    qualityChange = getAgedBrieQualityDelta(item);
                } else if (item.name.equals(BACKSTAGE_PASSES)) {
                    qualityChange = getBackstagePassQualityDelta(item);
                }
            }

            changeQuality(item, qualityChange);
        }
    }

    private int getNormalQualityDelta(Item item) {
        int qualityChange;
        if (item.sellIn >= 0) {
            qualityChange = -1;
        } else {
            qualityChange = -2;
        }
        return qualityChange;
    }

    private int getAgedBrieQualityDelta(Item item) {
        int qualityChange;
        if (item.sellIn >= 0) {
            qualityChange = 1;
        } else {
            qualityChange = 2;
        }
        return qualityChange;
    }

    private int getBackstagePassQualityDelta(Item item) {
        int qualityChange;
        if (item.sellIn < 0) {
            qualityChange = -1 * item.quality;  // By setting this to negative, it will set quality to zero in Change Quality method.
        } else if (item.sellIn < BACKSTAGE_TRIPLE_SELLIN) {
            qualityChange = 3;
        } else if (item.sellIn < BACKSTAGE_DOUBLE_SELLIN) {
            qualityChange = 2;
        } else {
            qualityChange = 1;
        }
        return qualityChange;
    }

    private void changeQuality (Item item, int change) {
        int changedQuality = item.quality + change;
        if (item.name.equals(SULFURAS)) {
            return;
        }
        if (changedQuality <= MAX_QUALITY && changedQuality >= 0) {
            item.quality = changedQuality;
        } else if (changedQuality > MAX_QUALITY ) {
            item.quality = MAX_QUALITY;
        } else if (changedQuality < 0) {
            item.quality = 0;
        }
    }
}
