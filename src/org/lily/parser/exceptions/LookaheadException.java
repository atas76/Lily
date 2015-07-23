package org.lily.parser.exceptions;

import org.lily.scanner.Token;

public class LookaheadException extends Exception {
	
	public LookaheadException(Token token) {
		super("Expected " + token.toString());
	}
}
