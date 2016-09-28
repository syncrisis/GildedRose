package com.gildedrose;

class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int BACKSTAGE_DOUBLE_SELLIN = 11;
    public static final int BACKSTAGE_TRIPLE_SELLIN = 6;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    protected boolean itemQualityDecreasesByOne (Item item) {
        return !item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES)
                && !item.name.equals(SULFURAS);
    }


    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            int qualityChange = 0;
            // If item changes it is not Sulfuras
            if (items[i].name != SULFURAS) {
                items[i].sellIn--;                 // Decrease sell in by 1

                // if item degrades, it is not Aged Brie or Backstage Passes
                if (items[i].name != AGED_BRIE && items[i].name != BACKSTAGE_PASSES) {
                    // If sell in is >= 0
                    if (items[i].sellIn >= 0) {
                        qualityChange = -1;
                    } else {
                        qualityChange = -2;
                    }
                } else if (items[i].name == AGED_BRIE) {
                    if (items[i].sellIn >= 0) {
                        qualityChange = 1;
                    } else {
                        qualityChange = 2;
                    }

                } else if (items[i].name == BACKSTAGE_PASSES) {
                    if (items[i].sellIn < 0) {
                        qualityChange = -1 * items[i].quality;
                    } else if (items[i].sellIn < BACKSTAGE_TRIPLE_SELLIN) {
                        qualityChange = 3;
                    } else if (items[i].sellIn < BACKSTAGE_DOUBLE_SELLIN) {
                        qualityChange = 2;
                    } else {
                        qualityChange = 1;
                    }
                }
                }
                changeQuality(items[i], qualityChange);

            }

        }



//            if (!items[i].name.equals("Aged Brie")
//                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                if (items[i].quality > 0) {
//                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                        items[i].quality = items[i].quality - 1;
//                    }
//                }
//            } else {
//                if (items[i].quality < 50) {
//                    items[i].quality = items[i].quality + 1;
//
//                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        if (items[i].sellIn < 11) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1;
//                            }
//                        }
//
//                        if (items[i].sellIn < 6) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1;
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                items[i].sellIn = items[i].sellIn - 1;
//            }
//
//            if (items[i].sellIn < 0) {
//                if (!items[i].name.equals("Aged Brie")) {
//                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        if (items[i].quality > 0) {
//                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                                items[i].quality = items[i].quality - 1;
//                            }
//                        }
//                    } else {
//                        items[i].quality = items[i].quality - items[i].quality;
//                    }
//                } else {
//                    if (items[i].quality < 50) {
//                        items[i].quality = items[i].quality + 1;
//                    }
//                }
//            }



    private void changeQuality (Item item, int change) {
        int changedQuality = item.quality + change;
        if (item.name == SULFURAS)
        {
            return;
        }
        if (changedQuality <= MAX_QUALITY && changedQuality >= 0) {
            item.quality = changedQuality;
        }
        else if (changedQuality > MAX_QUALITY )
        {
            item.quality = MAX_QUALITY;
        }
        else if (changedQuality < 0)
        {
            item.quality = 0;
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
