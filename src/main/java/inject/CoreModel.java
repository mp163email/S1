package inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.ws.util.io.helper.ClassHelper;
import disruptor.DisruptorMgr;
import event.GameEventMrg;
import event.GameEventMrgImpl;
import tick.ScheduleMrg;
import tick.ScheduleMrgImpl;
import tick.Ticker;
import tick.TickerImpl;
import timer.ImTimerMrg;
import timer.InTimerMrg;
import ws.TimerSchedule;

import java.util.List;

public class CoreModel extends AbstractModule {

    @Override
    protected void configure() {
        bind(DisruptorMgr.class).in(Singleton.class);
        bind(Ticker.class).to(TickerImpl.class).in(Singleton.class);
        bind(ScheduleMrg.class).to(ScheduleMrgImpl.class).in(Singleton.class);
        bind(InTimerMrg.class).to(ImTimerMrg.class).in(Singleton.class);
        bind(GameEventMrg.class).to(GameEventMrgImpl.class).in(Singleton.class);

        List<Class<?>> skills = ClassHelper.getAllClassBySuperClass("ws", TimerSchedule.class);
        for (Class<?> skill1 : skills) {
            bind(skill1).in(Singleton.class);
        }
    }
}
