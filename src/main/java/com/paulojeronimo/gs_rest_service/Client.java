package com.paulojeronimo.gs_rest_service;

import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.springframework.web.client.RestTemplate;

public class Client {
	public static void main (String[] args) throws UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));
    call("https", "gs-rest-service.paulojeronimo.com", "443", null, writer);
	}

  public static void call(String protocol, String host, String port, String name, PrintWriter writer) {
    if (protocol == null) protocol = "http";
    if (host == null) host = "localhost";
    if (port == null) port = "8081";

    RestTemplate restTemplate = new RestTemplate();

    String resource_url = protocol + "://" + host + ":" + port + "/greeting";
    if (name != null)
      resource_url += "?name=" + name;

    try {
      Value value = restTemplate.getForObject(resource_url, Value.class);
      writer.println(String.format("%s (%s)", value.getContent(), value.getId()));
    } catch (Exception e) {
      writer.println(String.format("Can't access \"%s\". Error:\n%s", resource_url, e.getMessage()));
    }
  }
}
