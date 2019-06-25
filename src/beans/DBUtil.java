package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil
{
    private String driver;
    private String url;
    private String username;
    private String password;
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public static void main(String[] args) {
    	/*DBUtil db = new DBUtil();
    	String[] params = {"19004","title4","introduction4","type4","F"};
    	String  sql = "insert into title values(?,?,?,?,?)";
    	int x = db.update(sql, params);
    	System.out.print(x);*/
    }
    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public DBUtil()
    {
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://152.136.115.81:3306/PMDB?useSSL=false";
        username = "root";
        password = "8520";
    }

    private Connection getConnection()
    {
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }

    private PreparedStatement getPrepareStatement(String sql)
    {
        try
        {
            pstmt = getConnection().prepareStatement(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return pstmt;
    }

    private void setParams(String sql, String[] params)
    {
        pstmt = this.getPrepareStatement(sql);
        if (params != null)
        {
            for (int i = 0; i < params.length; i++)
            {
                try
                {
                    pstmt.setString(i + 1, params[i]);
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public List getList(String sql, String[] params)
    {
        List list = new ArrayList();
        try
        {
            this.setParams(sql, params);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next())
            {
                Map m = new HashMap();
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    String colName = rsmd.getColumnName(i);
                    m.put(colName, rs.getString(colName));
                }
                list.add(m);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            close();
        }
        return list;
    }

    public Map getMap(String sql, String[] params)
    {
        List list = getList(sql, params);
        if (list.isEmpty())
            return null;
        else
            return (Map) list.get(0);

    }

    public int update(String sql, String[] params)
    {
        int recNo = 0;
        try
        {
            setParams(sql, params);
            recNo = pstmt.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            close();
        }
        return recNo;
    }

    private void close()
    {
        try
        {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e)
        {
        }
    }
}