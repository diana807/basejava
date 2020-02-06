package com.urise.webapp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name");
        response.getWriter().write(name == null ? "Hello resume" : "Hello " + name);
    }
}


//    Сделать реализацию SqlStorage.getAllSorted через 2 отдельных запроса: отдельно резюме и отдельно контакты.
//        Добавить в реализацию SqlStorage и в базу секции (кроме OrganizationSection). Для ListSection склеиваем строки через \n.
//        Сделать отображение таблицы резюме в сервлете (табл resume, т.е. только uuid и fullName).
//        HTML таблицы
// http://www.webremeslo.ru/html/glava4.html
