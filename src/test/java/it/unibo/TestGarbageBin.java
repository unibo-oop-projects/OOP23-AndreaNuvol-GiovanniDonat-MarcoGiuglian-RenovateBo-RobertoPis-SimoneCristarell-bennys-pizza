package it.unibo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.model.api.*;
import it.unibo.model.impl.*;
import it.unibo.model.impl.Management.SubtractorManager;

public class TestGarbageBin {
    
        // can throw a pizza in the garbage bin
    @Test
    public void testThrowPizzaInGarbageBin() throws ClassNotFoundException, InstantiationException, IllegalAccessError, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final PreparationZone p = new PreparationZoneImpl(new SubtractorManager());
        p.setNumberOfPizzasToPrepare(1);
        p.actionsOnIngredients("Dough", true, false);
        assertTrue(p.getPizza1().equals(new ArrayList<>(List.of("Dough"))));
        p.getGarbageBin().throwPizzaInGarbageBin(p, true);
        assertTrue(p.getPizza1().equals(new ArrayList<>(List.of())));

        
    }
}
