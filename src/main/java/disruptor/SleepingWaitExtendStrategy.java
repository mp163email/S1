package disruptor;

import com.lmax.disruptor.AlertException;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WaitStrategy;
import tick.Ticker;

import java.util.concurrent.locks.LockSupport;

public class SleepingWaitExtendStrategy implements WaitStrategy {

	private Ticker ticker;

	private static final int DEFAULT_RETRIES = 200;

	private final int retries;

	public SleepingWaitExtendStrategy(Ticker ticker) {
		this.retries = DEFAULT_RETRIES;
		this.ticker = ticker;
	}

	@Override
	public long waitFor(final long sequence, Sequence cursor, final Sequence dependentSequence, final SequenceBarrier barrier) throws AlertException, InterruptedException {
		long availableSequence;
		int counter = retries;
		while ((availableSequence = dependentSequence.get()) < sequence) {
			ticker.tick(System.currentTimeMillis());
			counter = applyWaitMethod(barrier, counter);
		}
		return availableSequence;
	}

	@Override
	public void signalAllWhenBlocking() {

	}

	private int applyWaitMethod(final SequenceBarrier barrier, int counterParam) throws AlertException {
		barrier.checkAlert();
		int counter = counterParam;

		if (counter > 100) {
			--counter;
		} else if (counter > 0) {
			--counter;
			Thread.yield();
		} else {
			LockSupport.parkNanos(1L);
		}

		return counter;
	}
}
