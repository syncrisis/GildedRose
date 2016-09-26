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



          if (!items[i].name.equals(SULFURAS)) {
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

                    if (items[i].name.equals(BACKSTAGE_PASSES)) {
                        backstageUpdate(items[i]);
                    }
                }
            }

            // If we are past the sell by date
            if (items[i].sellIn < 0) {
                if (itemQualityDecreasesByOne(items[i])) {
                    if (items[i].name.equals(BACKSTAGE_PASSES)) {
                        items[i].quality = 0;
                    }
                    if (!items[i].name.equals(BACKSTAGE_PASSES) &&
                            items[i].quality > 0 &&
                            !items[i].name.equals(SULFURAS)) {
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
