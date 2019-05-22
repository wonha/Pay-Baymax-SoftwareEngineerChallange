package com.wonha.paypay.challenge.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author Wonha Shin
 */
class ImmutableQueueTest {

	private final Queue<String> empty = ImmutableQueue.getEmptyInstance();
	private final Queue<String> one = ImmutableQueue.<String>getEmptyInstance()
				.enQueue("1");
	private final Queue<String> two = ImmutableQueue.<String>getEmptyInstance()
				.enQueue("1")
				.enQueue("2");

	// enQueue

	@Test
	void enQueueOnEmptyQueue() {
		Queue<String> newQueue = empty.enQueue("new element");
		assertTrue(newQueue != empty);
		assertEquals(1, ((Countable) newQueue).size());
	}

	@Test
	void enQueueOnQueue() {
		Queue<String> newQueue = one.enQueue("new element");
		assertTrue(newQueue != empty);
		assertEquals("1", newQueue.head());
	}

	@Test
	void enQueueNullElement() {
		Queue<String> newQueue = empty.enQueue(null);
		assertEquals(1, ((Countable) newQueue).size());
	}

	@Test
	void enQueueIdenticalElementTwice() {
		String elem = "new element";
		Queue<String> newQueue = empty.enQueue(elem).enQueue(elem);
		assertEquals(2, ((Countable) newQueue).size());
	}

	// deQueue

	@Test
	void deQueueOneElement() {
		Queue<String> newQueue = two.deQueue();
		assertEquals(1, ((Countable) newQueue).size());
		assertEquals("2", newQueue.head());
	}

	@Test
	void deQueueOnEmptyQueue() {
		Queue<String> newQueue = empty.deQueue();
		assertTrue(empty == newQueue);
	}

	@Test
	void deQueueThenIdentical() {
		assertTrue(empty == one.deQueue());
	}

	// head

	@Test
	void head() {
		assertEquals("1", one.head());
	}

	@Test
	void headOnEmptyQueue() {
		assertTrue(null == empty.head());
	}

	@Test
	void headOnNullContainingQueue() {
		Queue<String> newQueue = empty.enQueue(null);
		assertTrue(null == newQueue.head());
	}

	// isEmpty

	@Test
	void isEmptyOnEmptyQueue() {
		assertTrue(empty.isEmpty());
	}

	@Test
	void isEmptyOnOneElemQueue() {
		assertFalse(one.isEmpty());
	}

	// getEmptyInstance

	@Test
	void alwaysIdenticalEmptyQueue() {
		assertTrue(ImmutableQueue.getEmptyInstance() == ImmutableQueue.getEmptyInstance());
	}

}