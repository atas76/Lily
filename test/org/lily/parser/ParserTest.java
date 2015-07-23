package org.lily.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.lily.parser.exceptions.LookaheadException;
import org.lily.scanner.Analyzer;

public class ParserTest {
	
	private static final String resource = "resources/data/matches.liy";
	
	private String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	@Test
	public void testParser() throws Exception {
			
		Analyzer analyzer = new Analyzer(readFile(resource, StandardCharsets.UTF_8));
		Parser parser = new Parser(analyzer);
		parser.parse();
			
		List<List<ListItem>> items = parser.getDocument();
			
		List<ListItem> match16 = items.get(16);
		ListItem match16Home = match16.get(5);
		ListItem match16HomeGk = match16Home.getListContent().get(0);
		ListItem match16HomeDef = match16Home.getListContent().get(1);
		ListItem match16HomeMid = match16Home.getListContent().get(2);
		ListItem match16HomeAtt = match16Home.getListContent().get(3);
		ListItem match16HomeSubs = match16Home.getListContent().get(4);
			
		assertEquals("Martinez", match16HomeGk.getLiteral());
			
		assertEquals("Chambers", match16HomeDef.getListContent().get(0).getLiteral());
		assertEquals("Mertesacker", match16HomeDef.getListContent().get(1).getLiteral());
		assertEquals("Nacho Monreal", match16HomeDef.getListContent().get(2).getLiteral());
		assertEquals("Gibbs", match16HomeDef.getListContent().get(3).getLiteral());
			
		assertEquals("Ramsey", match16HomeMid.getListContent().get(0).getLiteral());
		assertEquals("Arteta", match16HomeMid.getListContent().get(1).getListContent().get(0).getLiteral());
		assertEquals("0.75", match16HomeMid.getListContent().get(1).getListContent().get(1).getLiteral());
		assertEquals("Oxlade-Chamberlain", match16HomeMid.getListContent().get(2).getLiteral());
		assertEquals("Cazorla", match16HomeMid.getListContent().get(3).getLiteral());
		assertEquals("Sanchez", match16HomeMid.getListContent().get(4).getLiteral());
			
		assertEquals("Sanogo", match16HomeAtt.getListContent().get(0).getLiteral());
		assertEquals("0.88", match16HomeAtt.getListContent().get(1).getLiteral());
			
		assertEquals("Flamini", match16HomeSubs.getListContent().get(0).getListContent().get(0).getLiteral());
		assertEquals("0.25", match16HomeSubs.getListContent().get(0).getListContent().get(1).getLiteral());
		assertEquals("M", match16HomeSubs.getListContent().get(0).getListContent().get(2).getLiteral());
		assertEquals("Podolski", match16HomeSubs.getListContent().get(1).getListContent().get(0).getLiteral());
		assertEquals("0.12", match16HomeSubs.getListContent().get(1).getListContent().get(1).getLiteral());
		assertEquals("F", match16HomeSubs.getListContent().get(1).getListContent().get(2).getLiteral()); 
	}
}
