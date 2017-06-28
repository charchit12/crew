<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.jdbc.Driver");%>

<HTML>
    <HEAD>
        <TITLE>Fetching Data From a Database</TITLE>
    </HEAD>

    <BODY>
        <H1>Fetching Data From a Database</H1>

        <% 
       
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", "root", "root");
        Statement statement = connection.createStatement();
        String fname = request.getParameter("fname");  
       ResultSet resultset = statement.executeQuery("select * from trainer where fname = '" + fname + "'") ; 

            if(!resultset.next()) {
                out.println("Sorry, could not find that publisher. ");
            } else {
        %>

        <TABLE BORDER="1">
            <TR>
               <TH>FNAME</TH>
               <TH>LNAME</TH>
               <TH>AGE</TH>
               <TH>EMAIL</TH>
               <TH>PASSWORD</TH>
               <TH>COUNTRY</TH>
           </TR>
           <TR>
               <TD> <%= resultset.getString(1) %> </TD>
               <TD> <%= resultset.getString(2) %> </TD>
               <TD> <%= resultset.getInt(3) %> </TD>
               <TD> <%= resultset.getString(4) %> </TD>
               <TD> <%= resultset.getString(5) %> </TD>
               <TD> <%= resultset.getString(6) %> </TD>
           </TR>
       </TABLE>
       <BR>
       <% 
           } 
       %>
    </BODY>
</HTML>