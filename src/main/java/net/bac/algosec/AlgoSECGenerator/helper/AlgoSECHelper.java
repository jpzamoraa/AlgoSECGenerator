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
	public static Set<String> generateAlgoSECRequest (final List<String> sources, final List<String> destinations,
			final List<String> ports, final String action) {
		final Set<String> result = new TreeSet<String>();

		for (final String destination : destinations) {
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
	private static Set<String> generatePorts(final List<String> ports, final String source, final String destination,
			final String action) {
		final TreeSet<String> result = new TreeSet<String>();

		for (final String port : ports) {
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
	private static Set<String> generateSources(final List<String> ports, final List<String> sources,
			final String destination, final String action) {
		final TreeSet<String> result = new TreeSet<String>();

		for (final String source : sources) {
			result.addAll(generatePorts(ports, source, destination, action));
		}

		return result;
	}

	/**
	 * Prueba de documentación
	 * 
	 * @param source
	 * @param destination
	 * @param port
	 * @param action
	 * @param delim
	 * @return String
	 */
	private static String generateAlgoSECLine(final String source, final String destination, final String port,
			final String action, final String delim) {
		final StringBuilder stb = new StringBuilder(source);
		stb.append(delim);
		stb.append(destination);
		stb.append(delim);
		stb.append(port);
		stb.append(delim);
		stb.append(action);
		return stb.toString();
	}
}
