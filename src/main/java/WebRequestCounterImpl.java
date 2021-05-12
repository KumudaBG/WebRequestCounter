package main.java;

import java.util.concurrent.atomic.LongAdder;

public class WebRequestCounterImpl implements WebRequestCounter {
	LongAdder counter;

	@Override
	public void increment() {
		counter.increment();
	}

	@Override
	public long getCurrentCount() {
		return counter.longValue();
	}

	@Override
	public void flush() {
		counter.reset();
	}

	public WebRequestCounterImpl() {
		counter = new LongAdder();
	}

}
