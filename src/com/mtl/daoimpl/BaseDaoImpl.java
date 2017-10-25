package com.mtl.daoimpl;

import com.mtl.util.DBUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoImpl {
    protected boolean update(String sql, Object... params){
        Connection con = DBUtil.getCon();
        PreparedStatement preStat = DBUtil.getPreStat(con, sql);
        DBUtil.setParams(preStat,params);
        try {
            if (preStat.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(null, preStat, con);
        }
        return false;
    }

    protected <T> List<T> query(Class<T> cls, String sql, Object...params){
        List<T> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        PreparedStatement preparedStatement = DBUtil.getPreStat(con, sql);
        DBUtil.setParams(preparedStatement, params);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()){
                T t = cls.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    BeanUtils.setProperty(t, metaData.getColumnLabel(i+1), resultSet.getObject(i+1));
                }
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(resultSet, preparedStatement, con);
        }
        return list;
    }

    protected int getCount(String sql, Object... params){
        Connection con = DBUtil.getCon();
        PreparedStatement preparedStatement = DBUtil.getPreStat(con, sql);
        DBUtil.setParams(preparedStatement, params);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(resultSet, preparedStatement, con);
        }
        return 0;
    }
}
