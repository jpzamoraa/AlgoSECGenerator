package net.bac.algosec.AlgoSECGenerator.helper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class AlgoSECHelperTest {

	private static String ACTION = "Allow";
	@Test
	public void generateAlgoSECRequestTest() {
		List<String> sources = Arrays.asList("10.120.5.121");
		List<String> destinations = Arrays.asList("10.137.2.74");
		List<String> ports = Arrays.asList("tcp/449");
		
		Set<String> set = AlgoSECHelper.generateAlgoSECRequest(sources, destinations, ports, ACTION);
		StringBuilder resultAsString = new StringBuilder();
		for (String line : set) {
			resultAsString.append(line);
		}
		
		assertEquals("10.120.5.121	10.137.2.74	tcp/449	Allow", resultAsString.toString());
	}
	
}
