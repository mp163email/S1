package disruptor;


import com.lmax.disruptor.EventHandler;

public class EventConsumer implements EventHandler<EventData> {
    @Override
    public void onEvent(EventData event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("disruptor--" + event.getEventNo());
    }
}
