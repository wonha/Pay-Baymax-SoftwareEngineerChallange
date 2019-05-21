package com.wonha.paypay.challenge.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author Wonha Shin
 */
class ImmutableQueueTest {

	final Queue<String> empty = ImmutableQueue.getEmptyInstance();
	final Queue<String> one = ImmutableQueue.<String>getEmptyInstance()
				.enQueue("1");
	final Queue<String> two = ImmutableQueue.<String>getEmptyInstance()
				.enQueue("1")
				.enQueue("2");

	// enQueue

	@Test
	public void enQueueNewElement() {
		Queue<String> newQueue = empty.enQueue("new element");
		assertTrue(newQueue != empty);
		assertEquals(1, ((Countable) newQueue).size());
	}

	@Test
	public void enQueueNullElement() {
		Queue<String> newQueue = empty.enQueue(null);
		assertEquals(1, ((Countable) newQueue).size());
	}

	@Test
	public void enQueueIdenticalElementTwice() {
		String elem = "new element";
		Queue<String> newQueue = empty.enQueue(elem).enQueue(elem);
		assertEquals(2, ((Countable) newQueue).size());
	}

	// deQueue

	@Test
	public void deQueueOneElement() {
		Queue<String> newQueue = two.deQueue();
		assertEquals(1, ((Countable) newQueue).size());
		assertEquals("2", newQueue.head());
	}

	@Test
	public void deQueueOnEmptyQueue() {
		Queue<String> newQueue = empty.deQueue();
		assertTrue(empty == newQueue);
	}

	@Test
	public void deQueueThenIdentical() {
		assertTrue(empty == one.deQueue());
	}

	// head

	@Test
	public void head() {
		assertEquals("1", one.head());
	}

	@Test
	public void headOnEmptyQueue() {
		assertTrue(null == empty.head());
	}

	@Test
	public void headOnNullContainingQueue() {
		Queue<String> newQueue = empty.enQueue(null);
		assertTrue(null == empty.head());
	}

	// isEmpty

	@Test
	public void isEmptyOnEmptyQueue() {
		assertTrue(empty.isEmpty());
	}

	@Test
	public void isEmptyOnOneElemQueue() {
		assertFalse(one.isEmpty());
	}

	// getEmptyInstance

	@Test
	public void alwaysIdenticalEmptyQueue() {
		assertTrue(ImmutableQueue.getEmptyInstance() == ImmutableQueue.getEmptyInstance());
	}

}