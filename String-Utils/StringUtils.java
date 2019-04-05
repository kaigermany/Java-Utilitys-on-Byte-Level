public class StringUtils{

public static boolean contains(String[] arr, String find) {
  for (String str : arr) if (str.equals(find)) return true;
  return false;
}

}
