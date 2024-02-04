package it.unibo.model.api;

public interface Menu {
    
    /**
     * Mostra il menu leggendolo direttamente dal file menu.json.
     * Deve essere usato solamente una volta, all'inizio.
     */
    void show();

    /**
     * Permette di aggiornare il menu in caso di aggiunta di una pizza.
     */
    void update();

}
