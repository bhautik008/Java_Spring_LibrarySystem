package edu.npu.cs548.exceptions;

public class UnknownResourceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnknownResourceException(String msg) {
		super(msg);
	}

}
