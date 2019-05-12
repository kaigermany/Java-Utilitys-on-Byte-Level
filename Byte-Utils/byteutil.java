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
	
	
	
	
class DynamicInputStream extends InputStream{
  ArrayList<Byte> buffer;
  volatile boolean isFinalized;
  int maxSize;
  public DynamicInputStream(int cacheSizeLimit){
    buffer = new ArrayList<Byte>();
    isFinalized=false;
    maxSize = cacheSizeLimit;
  }
  synchronized public boolean write(int b){
    if(b == -1) {
      setEOF();
      return false;
    }
   if(!isFinalized) {
     if(maxSize != -1) while(buffer.size() >= maxSize) delay(1);
     buffer.add(Byte.valueOf((byte)b));
   }
   return true;
  }
  synchronized public void setEOF(){
    isFinalized = true;
  }
  synchronized public int read(){
    if(buffer.size() != 0){
      return buffer.remove(0).byteValue();
    } else {
      if(isFinalized) return -1;
      while(buffer.size() == 0) {
        delay(1);
        if(isFinalized) return -1;
      }
      return buffer.remove(0).byteValue();
    }
  }
}

	
	
	
	
}
