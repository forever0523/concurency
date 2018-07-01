package com.juc;

/**
 * @desc:
 * @author: qwn48906
 * @date: 2018/5/21
 */
public class Product {
	private Long threadId;
	public Product(Long id) {
		threadId = id;
	}

	public Long getThreadId() {
		return threadId;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}
}
