package it.unibo.model.impl;

import it.unibo.model.api.Cleaner;

/**
 * Implementation of a preparation zone cleaner
 */
public class CleanerImpl implements Cleaner {

    @Override
    public void clean(final PreparationZoneImpl zone) {
        if (!zone.isDirty()) {
            throw new IllegalStateException("The preparation zone has to be dirty to call the cleaner");
        }
        zone.isDirty.remove(true);
    }
    
}
