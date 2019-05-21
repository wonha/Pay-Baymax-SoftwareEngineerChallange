package com.wonha.paypay.challenge.util;

/**
 * @author Wonha Shin
 */
public interface Stack<T> {

	Stack<T> push(T t);

	Stack<T> pop();

	T peek();

	boolean isEmpty();

}
