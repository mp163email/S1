package timer;

import java.util.PriorityQueue;


public class ImTimerMrg implements InTimerMrg {

    private final PriorityQueue<Timer> timerQueue = new PriorityQueue<Timer>(
        (Timer t1, Timer t2) -> {
            long triggerTime1 = t1.getTriggerTime();
            long triggerTime2 = t2.getTriggerTime();
            if (triggerTime1 > triggerTime2) {
                return 1;
            } else if (triggerTime1 == triggerTime2) {
                return 0;
            } else {
                return -1;
            }
        }
    );


    @Override
    public void addTimeer(Timer timer) {
        timer.setCreateTime(System.currentTimeMillis());
        timerQueue.offer(timer);
    }


    @Override
    public void tickTrigger(long mill) {
        for (;;) {
            Timer timer = timerQueue.peek();
            if (timer == null) {
                return;
            }
            if (mill < timer.getTriggerTime()) {
                return;
            }
            timer = timerQueue.poll();

            try {
                timer.setRemainExecuteNum(timer.getRemainExecuteNum() - 1);
                timer.trigger();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (timer.getRemainExecuteNum() <= 0) {
                continue;
            }
            timer.setCreateTime(mill);
            timerQueue.offer(timer);
        }
    }
}
