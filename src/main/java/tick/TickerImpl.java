package tick;

import inject.InjectUtilS;
import timer.InTimerMrg;

import javax.inject.Inject;

/**
 * @author miaopeng 20180512
 */
public class TickerImpl implements Ticker {

    private ScheduleMrg scheduleMrg;
    private InTimerMrg inTimerMrg;

    @Inject
    public TickerImpl () {
        scheduleMrg = InjectUtilS.getInjectObj(ScheduleMrg.class);
        inTimerMrg = InjectUtilS.getInjectObj(InTimerMrg.class);
    }

    @Override
    public void tick(long time) {
        scheduleMrg.tick(time);
        inTimerMrg.tickTrigger(time);
    }

}
