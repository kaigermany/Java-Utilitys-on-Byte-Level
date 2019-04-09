
import java.io.*;
import java.net.*;

class WebUtil {

  byte[] pingServer(String a) {
    return pingServer(a, "GET", null);
  }

  byte[] pingServer(String a, String method, byte[] post) {    
    try {
      URL url = new URL(a);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod(method);
      conn.setDoOutput(true);
      conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
      //conn.setRequestProperty("Accept", "*/*");
      conn.setRequestProperty("User-Agent", "Mozilla/4.0 (Windows NT 10.0; OSX; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
      conn.setRequestProperty("Accept-Language", "de,en-US;q=0.7,en;q=0.3");
      if (post != null) {
        if (post.length > 0) {
          conn.setRequestProperty("Content-Length", "" + post.length);
          OutputStream os = conn.getOutputStream();
          os.write(post);
          os.flush();
        }
      }
      BufferedInputStream dis = new BufferedInputStream(conn.getInputStream());
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      int chr = -1;
      byte[] buffer = new byte[1024];
      try {
        while ((chr = dis.read(buffer)) > 0)
          baos.write(buffer, 0, chr);
      } 
      catch (Exception e) {
        e.printStackTrace();
      }
      dis.close();
      return baos.toByteArray();
    } 
    catch (Exception e) {
      e.printStackTrace();
      return new byte[0];
    }
  }
  
}
