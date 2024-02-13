package it.unibo.model.impl;

import java.util.Optional;

import it.unibo.model.api.Cleaner;
import it.unibo.model.api.PreparationZone;

/**
 * Implementation of a preparation zone cleaner
 */
public class CleanerImpl implements Cleaner {

    @Override
    public void clean(final PreparationZone zone) {
        if (!zone.isDirty()) {
            throw new IllegalStateException("The preparation zone has to be dirty to call the cleaner");
        }
        zone.manageDirtyIngredients(Optional.empty());
    }

}
