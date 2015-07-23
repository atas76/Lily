package org.lily.scanner;

public enum Token {
	
	LIST_START('('),
	LIST_END(')'),
	COMMA(','),
	LITERAL('\0'),
	LIST_SEPARATOR(';');
	
	private char value;
	
	private Token(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return this.value;
	}
}
