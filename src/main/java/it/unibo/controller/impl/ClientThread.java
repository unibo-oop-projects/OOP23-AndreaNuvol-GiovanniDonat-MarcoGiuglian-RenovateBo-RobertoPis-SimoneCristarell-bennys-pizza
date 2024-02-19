package it.unibo.controller.impl;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.apache.commons.lang3.tuple.Pair;

import it.unibo.model.impl.Menu.MenuImpl.Pizza;
import it.unibo.view.GUIHallImpl;

public class ClientThread extends Thread{
    private final ControllerImpl controller;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Pair<Pizza, Optional<Pizza>> orderedPizzas;
    

    public ClientThread(final ControllerImpl controller){
        this.controller = controller;
    }

    @Override
    public void run() {
        while(true){
            lock.lock();
            try{
                orderedPizzas = controller.order();
                // sveglia altro thread
                GUIHallImpl.OrderThread.wakeUp();
                condition.await(); // go in waiting mode
                controller.pay(); // when terminated the waiting, he pay

            }catch(InterruptedException e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }

    public Pair<Pizza, Optional<Pizza>> getOrder(){
        return orderedPizzas;
    }

    public void wakeUp(){
        lock.lock();
        try{
            condition.signal();
        }finally{
            lock.unlock();
        }
    }
}
