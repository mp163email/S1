package ws;

import java.util.Calendar;

/**
 * Auth: miaopeng
 * Date: 2018-05-14 00:16:43
 */
public interface TimerSchedule {

    void onSecond(Calendar calendar);

    void onMinute(Calendar calendar);

    void onHour(Calendar calendar);

    void onDay(Calendar calendar);

    void onMonth(Calendar calendar);

    void onYear(Calendar calendar);
}
