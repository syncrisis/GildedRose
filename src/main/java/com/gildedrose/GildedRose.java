package com.gildedrose;

class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int BACKSTAGE_DOUBLE_SELLIN = 11;
    public static final int BACKSTAGE_TRIPLE_SELLIN = 6;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    protected boolean itemQualityDecreasesByOne (Item item) {
        return !item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT)
                && !item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }


    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {


            if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            // If this is an ordinary decrease quality by one
            if (itemQualityDecreasesByOne(items[i])) {
                if (items[i].quality > 0 ) {
                    items[i].quality = items[i].quality - 1;
//                    if (items[i].sellIn < 0 && items[i].quality > 0) {
//                        items[i].quality = items[i].quality - 1;
//                    }
                }
            // If this is not an ordinary item, increase by one or more
            } else {
                if (items[i].quality < MAX_QUALITY) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT)) {
                        backstageUpdate(items[i]);
                    }
                }
            }

            // If we are past the sell by date
            if (items[i].sellIn < 0) {
                if (itemQualityDecreasesByOne(items[i]) && ) {
                    if (!items[i].name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT) &&
                            items[i].quality > 0 &&
                            !items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                        items[i].quality = items[i].quality - 1;
                    }
                } else {
                    if (items[i].quality < MAX_QUALITY) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }

    private void backstageUpdate(Item item) {
        if (item.sellIn < BACKSTAGE_DOUBLE_SELLIN) {
            if (item.quality < MAX_QUALITY) {
                item.quality = item.quality + 1;
            }
        }

        if (item.sellIn < BACKSTAGE_TRIPLE_SELLIN) {
            if (item.quality < MAX_QUALITY) {
                item.quality = item.quality + 1;
            }
        }

        if (item.sellIn < 0)
        {
            item.quality = 0;
        }
    }
}
