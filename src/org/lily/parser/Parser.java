package org.lily.parser;

import java.util.ArrayList;
import java.util.List;

import org.lily.parser.exceptions.LookaheadException;
import org.lily.scanner.Analyzer;
import org.lily.scanner.Token;

public class Parser {
	
	private Analyzer analyzer;
	private List<List<ListItem>> document = new ArrayList<List<ListItem>>();
	
	private List<Token> tokens;
	private List<String> tokenValues;
	
	private int currentIndex = 0;
	
	private int listCounter = 0;
	
	public Parser(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	public int getCurrentListCounter() {
		return this.listCounter;
	}
	
	public List<List<ListItem>> getDocument() {
		return this.document;
	}
	
	public void parse() throws LookaheadException {
		
		this.analyzer.analyze();
	
		this.tokens = analyzer.getTokens();
		this.tokenValues = analyzer.getTokenValues();
		
		while (currentIndex < tokens.size()) {
			listCounter++;
			try {
				this.document.add(getNextList());
				lookahead(Token.LIST_SEPARATOR);
			} catch (LookaheadException le) {
				System.out.println("Current list index: " + listCounter);
				System.out.println("Current token: " + tokenValues.get(currentIndex));
				throw le;
			}
		}
	}
	
	private List<ListItem> getNextList() throws LookaheadException {
		
		lookahead(Token.LIST_START);
		List<ListItem> currentList = getNextListContent();
		lookahead(Token.LIST_END);
		
		return currentList;
	}
	
	private List<ListItem> getNextListContent() throws LookaheadException {
		
		List<ListItem> listContent = new ArrayList<ListItem>();
		
		ListItem currentListItem = getNextListItem();
		
		// System.out.println("Current list item: " + currentListItem);
		
		listContent.add(currentListItem);
		
		while (tokens.get(currentIndex).equals(Token.COMMA)) {
			this.currentIndex++;
			listContent.add(getNextListItem());
		}
		
		return listContent;
	}
	
	private ListItem getNextListItem() throws LookaheadException {
		
		ListItem listItem = new ListItem();
		
		switch(this.tokens.get(currentIndex)) {
		case LIST_START:
			currentIndex++;
			listItem.setListContent(getNextListContent());
			lookahead(Token.LIST_END);
			break;
		default:
			listItem.setLiteral(tokenValues.get(currentIndex++));
		}
		
		System.out.println("DEBUG: " + listItem);
		
		return listItem;
	}
	
	private void lookahead(Token token) throws LookaheadException {
		
		if (!tokens.get(currentIndex).equals(token)) {	
			throw new LookaheadException(token);
		}
		currentIndex++;
	}
}
