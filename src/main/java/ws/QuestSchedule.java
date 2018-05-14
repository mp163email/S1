package ws;

import javax.inject.Inject;
import java.util.Calendar;

/**
 * Auth: miaopeng
 * Date: 2018-05-14 00:19:00
 */
public class QuestSchedule implements TimerSchedule {

    private int expireSecond;

    @Inject
    public QuestSchedule () {
        expireSecond = (int)(System.currentTimeMillis() / 1000) + 30;
    }

    @Override
    public void onSecond(Calendar calendar) {
        int currSecond = (int)(calendar.getTimeInMillis() / 1000);
        if (expireSecond != 0 && currSecond > expireSecond) {
            System.out.println("任务过期");
            expireSecond = 0;
        }
    }

    @Override
    public void onMinute(Calendar calendar) {

    }

    @Override
    public void onHour(Calendar calendar) {

    }

    @Override
    public void onDay(Calendar calendar) {

    }

    @Override
    public void onMonth(Calendar calendar) {

    }

    @Override
    public void onYear(Calendar calendar) {

    }
}
