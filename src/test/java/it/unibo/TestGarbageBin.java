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
class TestGarbageBin {

    /**
     * Test if pizza is correctly garbaged.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessError
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    @Test
    void testThrowPizzaInGarbageBin() throws ClassNotFoundException, InstantiationException, IllegalAccessError,
     IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final PreparationZone p = new PreparationZoneImpl(new SubtractorManager());
        p.setNumberOfPizzasToPrepare(1);
        p.actionsOnIngredients("Dough", true, false);
        assertTrue(p.getPizza1().isEqual(new ArrayList<>(List.of("Dough"))));
        p.getGarbageBin().throwPizzaInGarbageBin(p, true);
        assertTrue(p.getPizza1().isEqual(new ArrayList<>(List.of())));
    }
}
