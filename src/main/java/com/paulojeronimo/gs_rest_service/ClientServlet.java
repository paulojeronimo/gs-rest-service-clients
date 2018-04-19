package com.paulojeronimo.gs_rest_service;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.RestTemplate;

@WebServlet("/")
public class ClientServlet extends HttpServlet {
  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String protocol = req.getParameter("protocol");
    if (protocol == null) protocol = "http";
    String host = req.getParameter("host");
    if (host == null) host = "localhost";
    String port = req.getParameter("port");
    if (port == null) port = "8081";
    String name = req.getParameter("name");

    PrintWriter writer = resp.getWriter();

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
