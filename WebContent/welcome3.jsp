<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.jdbc.Driver");%>

<HTML>
    <HEAD>
        <TITLE>Accessing the names in data base</TITLE>
    </HEAD>

    <BODY>
        <H1>NAMES REGISTERED YET</H1>

        <% 
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", "root", "root");                
        Statement statement = connection.createStatement() ;
        

              ResultSet resultset = statement.executeQuery("select fname from trainer") ;
                
        %>

        <TABLE BORDER="1">
            <TR>
                <TH>Name</TH>
            </TR>
            <% while(resultset.next()){ %>
                <TR>
                    <TD> 
                        <%= resultset.getString(1)%>  
                    </TD>
                </TR>
            <% } %>
        </TABLE>
    </BODY>
</HTML>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <TITLE>Database Lookup</TITLE>
    </HEAD>
 
    <BODY>
        <H1>WELCOME ADMIN</H1>
        <FORM ACTION="basic1.jsp" METHOD="POST">
            Please enter the First Name of the publisher you want to find:
            <BR>
            <INPUT TYPE="TEXT" NAME="fname">
            <BR>
            <INPUT TYPE="SUBMIT" value="Submit">
        </FORM>
    </BODY>
<HTML>