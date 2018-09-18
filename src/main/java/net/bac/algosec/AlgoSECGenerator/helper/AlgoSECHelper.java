package net.bac.algosec.AlgoSECGenerator.helper;
/**
 * 
 * @author josepablozamora
 *
 */

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class AlgoSECHelper {
	
	private static final String DELIM = "\t";
	/**
	 * 
	 * @param sources
	 * @param destinations
	 * @param ports
	 * @param action
	 * @return
	 */
	public static Set<String> generateAlgoSECRequest (List<String> sources, List<String> destinations, List<String> ports, String action) {
		Set<String> result = new TreeSet<String>();
		
		for (String destination : destinations) {
			result.addAll(generateSources(ports, sources, destination, action));
		}
		
		
		return result;
	}
	
	/**
	 * 
	 * @param ports
	 * @param source
	 * @param destination
	 * @param action
	 * @return
	 */
	private static Set<String> generatePorts (List<String> ports, String source, String destination, String action) {
		TreeSet<String> result = new TreeSet<String>();
		
		for (String port : ports) {
			result.add(generateAlgoSECLine(source, destination, port, action, DELIM)); 
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param ports
	 * @param sources
	 * @param destination
	 * @param action
	 * @return
	 */
	private static Set<String> generateSources (List<String> ports, List<String> sources, String destination, String action) {
		TreeSet<String> result = new TreeSet<String>();
		
		for (String source : sources) {
			 result.addAll(generatePorts(ports, source, destination, action));
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param port
	 * @param action
	 * @param delim
	 * @return
	 */
	private static String generateAlgoSECLine (String source, String destination, String port, String action, String delim) {
		StringBuilder stb = new StringBuilder(source);
		stb.append(delim);
		stb.append(destination);
		stb.append(delim);
		stb.append(port);
		stb.append(delim);
		stb.append(action);
		return stb.toString();
	}
}
