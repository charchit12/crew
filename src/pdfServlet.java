

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;

/**
 * Servlet implementation class document
 */
@WebServlet("/PdfServlet")
public class pdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
	response.setContentType("application/pdf");
	OutputStream out=response.getOutputStream();
	Connection con = (Connection) getServletContext().getAttribute("DBConnection");
	PreparedStatement ps=null;
	ResultSet rs = null;

	try
	{
		 ps = con.prepareStatement("select * from pdf ");
         rs = ps.executeQuery();
                 while (rs.next())
      {
		
		Document document =new Document();
		String name=rs.getString(1)+ rs.getString(2);
		PdfWriter.getInstance(document,out);
		document.open();
		document.add(new Paragraph(""));
		document.add(new Paragraph(""));
		document.add(new Paragraph(name));
		document.close();
      }
	}
	catch(DocumentException exc)
	{
		throw new IOException(exc.getMessage());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
