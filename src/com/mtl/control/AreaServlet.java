package com.mtl.control;

import com.google.gson.Gson;
import com.mtl.daoimpl.ExeCityImpl;
import com.mtl.pojo.Area;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/area")
public class AreaServlet extends BaseServlet {
    private ExeCityImpl exeCityImpl = new ExeCityImpl();
    protected void getAllProvince(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Area> list = exeCityImpl.getAllProvince();
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(list));
    }


    protected void getCountry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        System.out.println("pid = " + pid);
        List<Area> list = exeCityImpl.getAreaByPid(Integer.parseInt(pid));
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(list));
    }
}
