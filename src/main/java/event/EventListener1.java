package event;

public class EventListener1 implements GameEventListener {
    @Override
    public void onEvent(GameEvent event) {
        GameEvent1 obj = (GameEvent1)event;
        System.out.println(event.getClass().getName() + "的第一个监听" + obj.getObject());
    }
}