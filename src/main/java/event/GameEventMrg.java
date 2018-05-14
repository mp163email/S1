package event;


public interface GameEventMrg {


    void addEventListener(Class<? extends GameEvent> eventType, GameEventListener listener);

    void rmoveEventListener(Class<? extends GameEvent> eventType, GameEventListener listener);

    int getEventListenerNum(Class<? extends GameEvent> eventType);

    void runEvent(GameEvent eventType);

}
