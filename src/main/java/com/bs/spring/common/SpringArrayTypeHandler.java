package com.bs.spring.common;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpringArrayTypeHandler implements TypeHandler<String[]> {
    @Override
    public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        if(parameter != null && parameter.length > 0) {
            ps.setString(i, String.join(",",parameter));
        }else{
            ps.setString(i,null);
        }
    }

    @Override
    public String[] getResult(ResultSet rs, String columnName) throws SQLException {
        String lang = rs.getString(columnName);
        if(lang == null) {
            return lang.split(",");
        }else return null;
    }

    @Override
    public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String lang = rs.getString(columnIndex);
        if(lang == null) {
            return lang.split(",");
        }else return null;
    }
    
    @Override
    public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String lang = cs.getString(columnIndex);
        if(lang == null) {
            return lang.split(",");
        }else return null;
    }
}
