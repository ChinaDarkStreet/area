package com.mtl.daoimpl;

import com.mtl.pojo.Area;
import com.mtl.util.DBUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExeCityImpl extends BaseDaoImpl {
    public List<Area> getAreaByPid(int pid){
        List<Area> list = new ArrayList<>();
        list = query(Area.class, "select * from tb_da_area where pid = ?", pid);
        return list;
    }
    public  List<Area> getAllProvince(){
        List<Area> list = new ArrayList<>();
        list = query(Area.class, "select * from tb_da_area where pid = 0");
        return list;
    }

    public static void main(String[] args) {
        Document document = DocumentHelper.createDocument();
        Element country = document.addElement("China");
        Connection con = DBUtil.getCon();
        get(0, country, con);
        Writer fileWriter = null;
        OutputFormat prettyPrint = null;
        XMLWriter xmlWriter = null;
        try {
            fileWriter = new FileWriter("tb_da_area.xml");
            prettyPrint = OutputFormat.createPrettyPrint();
            xmlWriter = new XMLWriter(fileWriter, prettyPrint);
            xmlWriter.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                xmlWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void get(int pid, Element element, Connection con) {
        PreparedStatement preStat = DBUtil.getPreStat(con, "select * from tb_da_area where pid = " + pid);
        try {
            ResultSet resultSet = preStat.executeQuery();
            while (resultSet.next()){
                Element province = element.addElement(resultSet.getString("name").replace(" ", ""));
                province.addAttribute("id", String.valueOf(resultSet.getInt("id")));
                province.addAttribute("pid", String.valueOf(resultSet.getInt("pid")));
                get(resultSet.getInt("id"), province, con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
