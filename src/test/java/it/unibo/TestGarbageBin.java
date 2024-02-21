package it.unibo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.PreparationZoneImpl;
import it.unibo.model.impl.management.SubtractorManager;

/**
 * Test for the GarbageBinImpl.
 */
public class TestGarbageBin {

    /**
     * Test if pizza is correctly garbaged.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessError
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    @Test
    public void testThrowPizzaInGarbageBin() throws ClassNotFoundException, InstantiationException, IllegalAccessError,
     IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final PreparationZone p = new PreparationZoneImpl(new SubtractorManager());
        p.setNumberOfPizzasToPrepare(1);
        p.actionsOnIngredients("Dough", true, false);
        assertTrue(p.getPizza1().equals(new ArrayList<>(List.of("Dough"))));
        p.getGarbageBin().throwPizzaInGarbageBin(p, true);
        assertTrue(p.getPizza1().equals(new ArrayList<>(List.of())));
    }
}
