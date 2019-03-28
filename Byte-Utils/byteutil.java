/*
Created by KaiGermany 2019
Feel free to use any usefull line of Code.
*/


public class ByteUtil {

	public static boolean saveBytes(String file, byte[] data) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(file));
			fos.write(data);
			fos.flush();
			fos.close();
      return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    return false;
	}
  
	public static byte[] loadBytes(String f) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(f)));
			int len = -1;
      byte[] cache = new byte[4096];
			while ((len = bis.read(cache)) > 0) baos.write(cache, 0, len);
			bis.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
    return new byte[0];
	}

}
