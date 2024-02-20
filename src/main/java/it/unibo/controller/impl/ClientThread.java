package it.unibo.controller.impl;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.apache.commons.lang3.tuple.Pair;
import it.unibo.model.impl.Menu.MenuImpl.Pizza;
import it.unibo.view.GUIHallImpl;

/**
 * Thread that simulate a queue of clients.
 */
public class ClientThread extends Thread {
    private final ControllerImpl controller;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Pair<Pizza, Optional<Pizza>> orderedPizzas;

    /**
     * Constructor of the class ClientThread.
     * @param controller the controller of the app.
     */
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
                e.printStackTrace();
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
