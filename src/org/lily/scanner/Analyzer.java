package org.lily.scanner;

import java.util.ArrayList;
import java.util.List;

public class Analyzer {
	
	private String text;
	private List<Token> tokens = new ArrayList<Token>();
	private List<String> tokenValues = new ArrayList<String>();
	
	private final char LIST_START = '(';
	private final char LIST_END = ')';
	private final char COMMA = ',';
	private final char LIST_SEPARATOR = ';';
	
	private char [] chars;
	
	public Analyzer(String text) {
		this.text = text;
	}
	
	public List<Token> getTokens() {
		return this.tokens;
	}
	
	public List<String> getTokenValues() {
		return this.tokenValues;
	}
	
	public void analyze() {
		
		this.chars = new char[this.text.length()];
		this.text.getChars(0, this.text.length(), this.chars, 0);
		
		int currentIndex = 0;
		
		while (currentIndex < this.chars.length) {
			currentIndex = scan(currentIndex);
		}
	}
	
	private int scan(int startIndex) {
	
		int charIndex;
		
		for (charIndex = startIndex; charIndex < chars.length; charIndex++) { 
			
			char c = chars[charIndex];
			
			if (Character.isWhitespace(c)) continue;
			
			switch(c) {
			case LIST_START:
				this.tokens.add(Token.LIST_START);
				addCharacterToken(charIndex, c);
				break;
			case LIST_END:
				this.tokens.add(Token.LIST_END);
				addCharacterToken(charIndex, c);
				break;
			case COMMA:
				this.tokens.add(Token.COMMA);
				addCharacterToken(charIndex, c);
				break;
			case LIST_SEPARATOR:
				this.tokens.add(Token.LIST_SEPARATOR);
				addCharacterToken(charIndex, c);
				break;
			default:
				this.tokens.add(Token.LITERAL);
				charIndex = scanLiteral(charIndex);
			}
		}
		
		return charIndex;
	}
	
	private int scanLiteral(int startIndex) {
		
		int charIndex;
		
		StringBuilder literal = new StringBuilder();
		
		/**
		 * Add each character to the current token literal if applicable; 
		 * halt if a special character is found and backtrack the index so that it will be scanned by the calling method 
		 */
		for (charIndex = startIndex; charIndex < chars.length; charIndex++) {
			
			char c = chars[charIndex];
			
			switch(c) {
			case LIST_START:
			case LIST_END:
			case COMMA:
			case LIST_SEPARATOR:
				this.tokenValues.add(literal.toString());
				return charIndex - 1;
			default: // literal
				literal.append(String.valueOf(c));
			}
		}
		
		this.tokenValues.add(literal.toString());
		return charIndex;
	}
	
	private void addCharacterToken(int nextIndex, char c) {
		this.tokenValues.add(String.valueOf(c));
	}
}
