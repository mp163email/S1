package tick;

import com.ws.util.io.helper.ClassHelper;
import inject.InjectUtilS;
import ws.TimerSchedule;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class ScheduleMrgImpl implements ScheduleMrg {

    private List<TimerSchedule> timeScheduleList = new LinkedList<TimerSchedule>();

    private Calendar calendar = Calendar.getInstance();

    private long lastSecond;

    private int lastMinute;

    private int lastHour;

    private int lastDay;

    private int lastMonth;

    private int lastYear;

    @Inject
    public ScheduleMrgImpl () {
        List<Class<?>> timerList = ClassHelper.getAllClassBySuperClass("ws", TimerSchedule.class);
        for (Class<?> timer : timerList) {
            TimerSchedule timerSchedule = (TimerSchedule) InjectUtilS.getInjectObj(timer);
            timeScheduleList.add(timerSchedule);
        }
    }

    @Override
    public void tick(long time) {
        if (lastSecond + 1000 < time) {
            lastSecond = time;
            onSecond(time);
        }
    }


    private void onSecond (long currMill) {
        System.out.println("---second---" + calendar.get(Calendar.SECOND));
        calendar.setTimeInMillis(currMill);

        for (TimerSchedule timerSchedule : timeScheduleList) {
            timerSchedule.onSecond(calendar);
        }

        if (lastMinute != calendar.get(Calendar.MINUTE)) {
            lastMinute = calendar.get(Calendar.MINUTE);
            onMinute();
        }
    }

    private void onMinute () {
        System.out.println("---minute---" + calendar.get(Calendar.MINUTE));
        if (lastHour != calendar.get(Calendar.HOUR)) {
            lastHour = calendar.get(Calendar.HOUR);
            onHour();
        }
    }

    private void onHour () {
        if (lastDay != calendar.get(Calendar.DAY_OF_MONTH)) {
            lastDay = calendar.get(Calendar.DAY_OF_MONTH);
            onDay();
        }
    }

    private void onDay () {
        if (lastMonth != calendar.get(Calendar.MONTH)) {
            lastMonth = calendar.get(Calendar.MONTH);
            onMonth();
        }
    }

    private void onMonth () {
        if (lastYear != calendar.get(Calendar.YEAR)) {
            lastYear = calendar.get(Calendar.YEAR);
            onYear();
        }
    }

    private void onYear () {

    }

}
