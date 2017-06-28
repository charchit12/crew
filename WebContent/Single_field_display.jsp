<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.jdbc.Driver");%>

<HTML>
    <HEAD>
        <TITLE>accessing the names in data base</TITLE>
    </HEAD>

    <BODY>
        <H1>Fname in database</H1>

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