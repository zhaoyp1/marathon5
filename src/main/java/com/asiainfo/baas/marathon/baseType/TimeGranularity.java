package com.asiainfo.baas.marathon.baseType;

/**
 * Sampling rate of the collection or production of perfomance indicators.
 * 
 * Supported values are : 1mn, 5mn, 15mn, 30mn, 1h, 24h, not applicable. The values 1 month and 1 year are also defined even if not used for collection.
 * Note that literals cannot start by a digit, so they are prefixed by a letter.
 * For current instantaneous measurements the granularity does not apply which is indicated by the value "NA" (Not Applicable).
 */
public enum TimeGranularity {
    G_1MN, G_5MN, G_15MN, G_30MN, G_1H, G_24H, G_1M, G_1Y, NA
}