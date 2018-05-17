package com.github.mjeanroy.wc18.api.exceptions;

public class MatchNotFoundException extends RuntimeException {

	private final String id;

	public MatchNotFoundException(String id) {
		super(String.format("Cannot find match '%s'", id));
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
