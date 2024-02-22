package it.unibo.controller.impl;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.apache.commons.lang3.tuple.Pair;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.impl.MenuImpl.Pizza;
import it.unibo.view.GUIHallImpl;

/**
 * Thread that simulate a queue of clients.
 */
public class ClientThread extends Thread {
    private final ControllerImpl controller;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Pair<Pizza, Optional<Pizza>> orderedPizzas;
    private final Logger logger = Logger.getLogger(ClientThread.class.getName());

    /**
     * Constructor of the class ClientThread.
     * @param controller the controller of the app.
     */
    @SuppressFBWarnings(
            value = { "EI_EXPOSE_REP2"},
            justification = "trying to resolve the warning, we noticed that the game was"
            + " causing several problems, for example labels etc. were not shown"
        )
    public ClientThread(final ControllerImpl controller) {
        this.controller = controller;
    }

    /**
     * The client makes his/her order and wakes up the thread that shows it, then it waits until
     * the kitchen hasn't made the pizzas.
     */
    @Override
    public void run() {
        while (true) {
            this.lock.lock();
            try {
                this.orderedPizzas = controller.order();
                GUIHallImpl.OrderThread.wakeUp();
                this.condition.await();
                this.controller.pay();
            } catch (InterruptedException e) {
                logger.log(Level.INFO, e.toString());
            } finally {
                this.lock.unlock();
            }
        }
    }

    /**
     * @return one and sometimes two pizzas.
     */
    public Pair<Pizza, Optional<Pizza>> getOrder() {
        return this.orderedPizzas;
    }

    /**
     * It wakes up this thread.
     */
    public void wakeUp() {
        this.lock.lock();
        try {
            this.condition.signal();
        } finally {
            this.lock.unlock();
        }
    }
}
