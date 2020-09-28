package edu.it.controllers;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;


public class AlumnosController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("respuesta get"); 
        response.setStatus(200);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("respuesta post"); 
        response.setStatus(200);
    }
    public void doPut(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
    
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("respuesta put"); 
        response.setStatus(200);

    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
    
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("respuesta delete"); 
        response.setStatus(200);
    }
}
