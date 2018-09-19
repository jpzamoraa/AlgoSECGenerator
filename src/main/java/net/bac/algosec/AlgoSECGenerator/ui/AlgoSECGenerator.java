package net.bac.algosec.AlgoSECGenerator.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.bac.algosec.AlgoSECGenerator.helper.AlgoSECHelper;

public class AlgoSECGenerator {
	
	private static List<String> sources = Arrays.asList("10.120.5.121", "10.120.11.189", "10.120.11.114", "10.120.11.76","10.120.11.158","10.120.5.187",
			"10.120.11.204","10.120.34.39","10.120.11.220","10.120.11.242","10.120.11.139","10.120.11.182","10.120.34.28","10.120.5.126","10.120.11.215",
			"10.120.11.216","10.120.11.78","10.120.11.55","10.120.11.70","10.120.5.119","10.120.11.190","10.120.11.176","10.120.11.113","10.120.11.252","10.120.11.245",
			"10.120.11.124","10.120.34.227","10.120.11.116","10.120.5.139","10.136.6.12","10.187.5.37","10.136.6.13","10.187.5.33","10.120.11.193","10.120.5.118",
			"10.120.5.123","10.120.11.201","10.120.11.159");
	private static List<String> destinations = Arrays.asList("10.137.2.74");
	private static List<String> ports = Arrays.asList("tcp/449", "tcp/137","tcp/445","tcp/139","tcp/8470","tcp/8471","tcp/8472","tcp/8473","tcp/8474","tcp/8475","tcp/8476","tcp/9473","tcp/9475","tcp/9470","tcp/9476");
	private static String ACTION = "Allow";
	
	
	
	public static void main(String[] args) {
		Set<String> set = AlgoSECHelper.generateAlgoSECRequest(sources, destinations, ports, ACTION);
		for (String line : set) {
			System.out.println(line);
			s:
		}
	}

}
