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
    Client.call(
      req.getParameter("protocol"),
      req.getParameter("host"),
      req.getParameter("port"),
      req.getParameter("name"),
      resp.getWriter());
	}
}
