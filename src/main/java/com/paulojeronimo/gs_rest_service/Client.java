package com.paulojeronimo.gs_rest_service;

import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.springframework.web.client.RestTemplate;

public class Client {
	public static void main (String[] args) throws UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));
    String[] params = new String[4];
    if (args.length > 0) {
      if (args.length == 1)
        params = parse(args[0]);
      else if (args.length == 4) {
        for (int i = 0; i < 4; i++) params[i] = args[i];
      } else {
        System.out.println("Invalid number of parameters: must be 0, 1 or 4");
        System.exit(1);
      }
    }
    call(params[0], params[1], params[2], params[3], writer);
	}

  public static String[] parse(String url) {
    String[] result = new String[4];
    System.out.println("TODO: parse an URL must be implemented. Calling with default parameters ...");
    return result;
  }

  public static void call(String protocol, String host, String port, String name, PrintWriter writer) {
    if (protocol == null || protocol.equals("")) protocol = "http";
    if (host == null || host.equals("")) host = "localhost";
    if (port == null || port.equals("")) port = "8081";

    RestTemplate restTemplate = new RestTemplate();

    String resource_url = protocol + "://" + host + ":" + port + "/greeting";
    if (name != null && !name.equals(""))
      resource_url += "?name=" + name;

    String result;
    try {
      Value value = restTemplate.getForObject(resource_url, Value.class);
      result = String.format("%s (%s)", value.getContent(), value.getId());
      writer.println(result);
      System.out.println(result);
    } catch (Exception e) {
      result = String.format("Can't access \"%s\". Error:\n%s", resource_url, e.getMessage());
      writer.println(result);
      System.out.println(result);
    }
  }
}
