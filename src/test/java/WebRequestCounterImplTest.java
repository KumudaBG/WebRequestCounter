package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.LongAdder;

import org.junit.Before;
import org.junit.Test;

import main.java.WebRequestCounterImpl;

public class WebRequestCounterImplTest {

	WebRequestCounterImpl vounterImpl;
	Field field;

	@Before
	public void setUp() {
		try {
			Class<?> counterImplClass = Class.forName("main.java.WebRequestCounterImpl");
			vounterImpl = (WebRequestCounterImpl) counterImplClass.getConstructor().newInstance();
			field = counterImplClass.getDeclaredField("counter");
			field.setAccessible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_increment() {

		// increment counter twice
		vounterImpl.increment();
		vounterImpl.increment();

		// get the counter value using reflect.Field
		LongAdder counter = null;
		try {
			counter = (LongAdder) field.get(vounterImpl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(2, counter.intValue());

	}

	@Test
	public void test_flush() {

		try {
			// increment method called once
			vounterImpl.increment();

			LongAdder counter = (LongAdder) field.get(vounterImpl);
			assertEquals(1, counter.intValue());

			// flush method called once
			vounterImpl.flush();

			counter = (LongAdder) field.get(vounterImpl);
			assertEquals(0, counter.intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_getCurrentCount() {
		try {
			// increment method called twice
			vounterImpl.increment();
			vounterImpl.increment();
			LongAdder counter = (LongAdder) field.get(vounterImpl);
			assertEquals(vounterImpl.getCurrentCount(), counter.longValue());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
