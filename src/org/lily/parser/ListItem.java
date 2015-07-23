package org.lily.parser;

import java.util.List;

public class ListItem {
	
	private String literal;
	private List<ListItem> listContent;
	
	private boolean nestedList = false;
	
	public void setLiteral(String literal) {
		this.literal = literal;
	}
	
	public void setListContent(List<ListItem> listContent) {
		this.listContent = listContent;
		this.nestedList = true;
	}
	
	public boolean isNestedList() {
		return this.nestedList;
	}
	
	public String getLiteral() {
		return this.literal;
	}
	
	public List<ListItem> getListContent() {
		return this.listContent;
	}
	
	@Override
	public String toString() {
		if (!nestedList) {
			return this.literal;
		} else {
			
			StringBuilder retVal = new StringBuilder();
			retVal.append("(");
			for (ListItem item: this.listContent) {
				retVal.append(item.toString());
				retVal.append(",");
			}
			retVal.deleteCharAt(retVal.length() - 1);
			retVal.append(")");
			return retVal.toString();
		}
	}
}
