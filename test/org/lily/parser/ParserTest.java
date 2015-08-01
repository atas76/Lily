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
		
		List<ListItem> match6 = items.get(6);
		ListItem match6Away = match6.get(6);
		ListItem match6AwayMid = match6Away.getListContent().get(2);
		
		assertEquals("Sam", match6AwayMid.getListContent().get(0).getListContent().get(0).getLiteral());
		assertEquals("0.87", match6AwayMid.getListContent().get(0).getListContent().get(1).getLiteral());
		
		assertEquals("Boateng", match6AwayMid.getListContent().get(1).getLiteral());
		
		assertEquals("Meyer", match6AwayMid.getListContent().get(2).getListContent().get(0).getLiteral());
		assertEquals("0.82", match6AwayMid.getListContent().get(2).getListContent().get(1).getLiteral());
		
		assertEquals("Aogo", match6AwayMid.getListContent().get(3).getLiteral());
		
		assertEquals("Draxler", match6AwayMid.getListContent().get(4).getListContent().get(0).getLiteral());
		assertEquals("0.96", match6AwayMid.getListContent().get(4).getListContent().get(1).getLiteral());
		
		List<ListItem> match = items.get(0);
		ListItem matchAway = match.get(6);
		ListItem matchAwaySubs = matchAway.getListContent().get(4);
		
		assertEquals("Oxlade-Chamberlain", matchAwaySubs.getListContent().get(0).getListContent().get(0).getLiteral());
		assertEquals("0.23", matchAwaySubs.getListContent().get(0).getListContent().get(1).getLiteral());
		assertEquals("F", matchAwaySubs.getListContent().get(0).getListContent().get(2).getLiteral());
		
		assertEquals("Podolski", matchAwaySubs.getListContent().get(1).getListContent().get(0).getLiteral());
		assertEquals("0.12", matchAwaySubs.getListContent().get(1).getListContent().get(1).getLiteral());
		assertEquals("F", matchAwaySubs.getListContent().get(1).getListContent().get(2).getLiteral());
		
		assertEquals("Rosicky", matchAwaySubs.getListContent().get(2).getListContent().get(0).getLiteral());
		assertEquals("0.08", matchAwaySubs.getListContent().get(2).getListContent().get(1).getLiteral());
		assertEquals("M", matchAwaySubs.getListContent().get(2).getListContent().get(2).getLiteral());
		
		List<ListItem> match2 = items.get(2);
		ListItem match2Home = match2.get(5);
		
		assertEquals("De Gea", match2Home.getListContent().get(0).getLiteral());
		
		List<ListItem> match9 = items.get(9);
		ListItem match9Away = match9.get(6);
		ListItem match9AwayForwards = match9Away.getListContent().get(3);
		
		assertEquals("Choupo-Moting", match9AwayForwards.getListContent().get(0).getListContent().get(0).getLiteral());
		assertEquals("0.32", match9AwayForwards.getListContent().get(0).getListContent().get(1).getLiteral());
		
		assertEquals("Huntelaar", match9AwayForwards.getListContent().get(1).getLiteral());
		
		List<ListItem> match15 = items.get(15);
		ListItem match15Home = match15.get(5);
		
		assertEquals("Weidenfeller", match15Home.getListContent().get(0).getLiteral());
		
		ListItem match15HomeDefenders = match15Home.getListContent().get(1);
		ListItem match15HomeMidfielders = match15Home.getListContent().get(2);
		ListItem match15HomeForwards = match15Home.getListContent().get(3);
		ListItem match15HomeSubs = match15Home.getListContent().get(4);
		
		assertEquals("Durm", match15HomeDefenders.getListContent().get(0).getLiteral());
		assertEquals("Subotic", match15HomeDefenders.getListContent().get(1).getLiteral());
		assertEquals("Papastathopoulos", match15HomeDefenders.getListContent().get(2).getLiteral());
		assertEquals("Schmelzer", match15HomeDefenders.getListContent().get(3).getLiteral());
		
		assertEquals("Aubameyang", match15HomeMidfielders.getListContent().get(0).getLiteral());
		assertEquals("Bender", match15HomeMidfielders.getListContent().get(1).getLiteral());
		assertEquals("Kehl", match15HomeMidfielders.getListContent().get(2).getLiteral());
		assertEquals("Groskreutz", match15HomeMidfielders.getListContent().get(3).getLiteral());
		assertEquals("Mkhitaryan", match15HomeMidfielders.getListContent().get(4).getLiteral());
		
		assertEquals("Immobile", match15HomeForwards.getLiteral());
		
		assertEquals("Ginter", match15HomeSubs.getListContent().get(0).getListContent().get(0).getLiteral());
		assertEquals("0.5", match15HomeSubs.getListContent().get(0).getListContent().get(1).getLiteral());
		assertEquals("D", match15HomeSubs.getListContent().get(0).getListContent().get(2).getLiteral());
		
		assertEquals("Jojic", match15HomeSubs.getListContent().get(1).getListContent().get(0).getLiteral());
		assertEquals("0.12", match15HomeSubs.getListContent().get(1).getListContent().get(1).getLiteral());
		assertEquals("M", match15HomeSubs.getListContent().get(1).getListContent().get(2).getLiteral());
		
		assertEquals("Ramos", match15HomeSubs.getListContent().get(2).getListContent().get(0).getLiteral());
		assertEquals("0.04", match15HomeSubs.getListContent().get(2).getListContent().get(1).getLiteral());
		assertEquals("F", match15HomeSubs.getListContent().get(2).getListContent().get(2).getLiteral());
		
		List<ListItem> match8 = items.get(8);
		ListItem match8Home = match8.get(5);
		ListItem match8HomeDefenders = match8Home.getListContent().get(1);
		
		assertEquals("Uchida", match8HomeDefenders.getListContent().get(0).getLiteral());
		assertEquals("Howedes", match8HomeDefenders.getListContent().get(1).getLiteral());
		assertEquals("Matip", match8HomeDefenders.getListContent().get(2).getLiteral());
		assertEquals("Nastasic", match8HomeDefenders.getListContent().get(3).getLiteral());
		assertEquals("Aogo", match8HomeDefenders.getListContent().get(4).getLiteral());
		
		List<ListItem> match1 = items.get(1);
		ListItem match1Home = match1.get(5);
		ListItem match1HomeMidfielders = match1Home.getListContent().get(2);
		
		assertEquals("Ramsey", match1HomeMidfielders.getListContent().get(0).getListContent().get(0).getLiteral());
		assertEquals("0.86", match1HomeMidfielders.getListContent().get(0).getListContent().get(1).getLiteral());
		
		assertEquals("Arteta", match1HomeMidfielders.getListContent().get(1).getLiteral());
		
		assertEquals("Oxlade-Chamberlain", match1HomeMidfielders.getListContent().get(2).getLiteral());
		
		assertEquals("Wilshere", match1HomeMidfielders.getListContent().get(3).getListContent().get(0).getLiteral());
		assertEquals("0.61", match1HomeMidfielders.getListContent().get(3).getListContent().get(1).getLiteral());
		
		assertEquals("Sanchez", match1HomeMidfielders.getListContent().get(4).getLiteral());
		
		assertEquals("Arsenal", match1.get(0).getLiteral());
		assertEquals("Manchester United", match1.get(1).getLiteral());
		assertEquals("1", match1.get(2).getLiteral());
		assertEquals("2", match1.get(3).getLiteral());
		assertEquals("22-11-2014", match1.get(4).getLiteral());
		
		List<ListItem> match7 = items.get(7);
		ListItem match7Away = match7.get(6);
		
		ListItem match1AwayMidfielders = match7Away.getListContent().get(2);
		
		assertEquals("Fabregas", match1AwayMidfielders.getListContent().get(0).getLiteral());
		assertEquals("Matic", match1AwayMidfielders.getListContent().get(1).getLiteral());
		assertEquals("Willian", match1AwayMidfielders.getListContent().get(2).getLiteral());
		
		assertEquals("Oscar", match1AwayMidfielders.getListContent().get(3).getListContent().get(0).getLiteral());
		assertEquals("0.83", match1AwayMidfielders.getListContent().get(3).getListContent().get(1).getLiteral());
		
		assertEquals("Hazard", match1AwayMidfielders.getListContent().get(4).getLiteral());
		
	}
}
