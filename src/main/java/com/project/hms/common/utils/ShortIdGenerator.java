package com.project.hms.common.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.security.SecureRandom;

public class ShortIdGenerator implements IdentifierGenerator {
    private static volatile SecureRandom rnd = null;
    private static final long MSB = 0x8000000000000000L;

    @Override
    public Object generate( SharedSessionContractImplementor session, Object object ) {
        SecureRandom generator = rnd;
        if (generator == null) {
            rnd = generator = new SecureRandom();
        }
        return Long.toHexString(MSB | generator.nextLong());
    }

}
