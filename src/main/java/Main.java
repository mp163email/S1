import disruptor.DisruptorMgr;
import event.*;
import inject.InjectUtilS;
import timer.*;

public class Main {

    public static void main(String[] args) {

        //inject
        InjectUtilS.createInjector();

        //disruptor
        new Thread(() -> {
            DisruptorMgr disruptorMgr = InjectUtilS.getInjectObj(DisruptorMgr.class);
            disruptorMgr.product();
        }).start();

        //timer
        InTimerMrg timerMrg = InjectUtilS.getInjectObj(InTimerMrg.class);
        timerMrg.addTimeer(new TimerPing(5 * 1000L, Integer.MAX_VALUE, new ImTimerCallBackPing()));
        timerMrg.addTimeer(new TimerPing2(10 * 1000L, Integer.MAX_VALUE, new ImTimerCallBackPing2()));

        //event
        GameEvent1 gameEvent1 = new GameEvent1();
        gameEvent1.setObject(new String("My name is gameEvent1"));
        GameEventMrg gameEventMrg = new GameEventMrgImpl();
        gameEventMrg.addEventListener(gameEvent1.getClass(), new EventListener1());
        gameEventMrg.runEvent(gameEvent1);
    }
}
