package com.taqnihome.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is Generator class
 * Created by dhiren on 31/7/16
 * @author dhiren
 * @since 31/07/2016
 * @see UUID
 */

public class Generator {


    /**
     * This method is use for generate Id for model class
     * @return String
     */
    public synchronized static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return Long.toHexString(uuid.getMostSignificantBits()) + Long.toHexString(uuid.getLeastSignificantBits());
    }


    /**
     * This method is used for generate otp
     * @return String
     */
    public synchronized static String generateOtp()
    {
        return String.valueOf(ThreadLocalRandom.current().ints(1000,10000).distinct().limit(1).findFirst().getAsInt());
    }
}
