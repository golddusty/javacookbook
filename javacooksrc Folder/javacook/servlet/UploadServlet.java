import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class UploadServlet extends HttpServlet {

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
	throws IOException, javax.servlet.ServletException {

		PrintWriter out = response.getWriter();
		Enumeration headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String name = headers.nextElement().toString();
			out.println("<li>" + name + " --> " +
				request.getHeader(name));
		}
		out.println("<HR>");
		int size = request.getIntHeader("content-length");
		// if (size > some_threshold) {
		//	out.println("<P>Too big!</P>");
		//	return;
		// }
		File f = new File("/tmp/abc.upload");
		//if (!f.createNewFile()) {
		//	out.println("File already exists");
		//	return;
		//}
		InputStream is = request.getInputStream();

		// DANGER WILL ROBINSON!! YOU MUST ADD CODE HERE TO
		// CHECK FOR TRAPS LIKE . or / or \ or : or File.separator in the filename

		OutputStream os = new FileOutputStream(f.getPath());
		
		byte[] b = new byte[1024];
		int n;
		while ((n = is.read(b)) > 0)
			os.write(b, 0, n);
		is.close();
		os.close();
		out.println("Successfully uploaded!");
	}
}
