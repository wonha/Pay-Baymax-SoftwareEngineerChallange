package com.wonha.paypay.challenge.util;

import java.util.Iterator;

/**
 * @author Wonha Shin
 */
public interface StackIterator<T> extends Iterator<T> {

	StackIterator<T> pop();

}
