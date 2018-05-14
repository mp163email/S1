package disruptor;

import com.google.inject.Inject;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import tick.ScheduleMrgImpl;
import tick.Ticker;
import tick.TickerImpl;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DisruptorMgr {

    private Disruptor<EventData> disruptor = null;

    @Inject
    public DisruptorMgr() {
        init();
    }

    public void init () {
        Ticker ticker = new TickerImpl();
        ExecutorService executor = Executors.newSingleThreadExecutor((Runnable r) -> new Thread (r, "Logic_Thread"));
        disruptor = new Disruptor<>(() -> new EventData(), 1024, executor, ProducerType.MULTI, new SleepingWaitExtendStrategy(ticker));
        disruptor.handleEventsWith(new EventConsumer());
        disruptor.start();
    }

    public void product () {
        ExecutorService executor1 = Executors.newCachedThreadPool();
        int i = 5;
        while (i > 0) {
            i--;
            executor1.execute(()->{
                RingBuffer<EventData> ringBuffer = disruptor.getRingBuffer();
                long sequence = ringBuffer.next();
                try {
                    EventData event = ringBuffer.get(sequence);
                    event.setEventNo(new Random().nextInt(100));
                } finally {
                    ringBuffer.publish(sequence);
                }

            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
