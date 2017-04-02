package online.themixhub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import online.themixhub.MiscUtils;

public class MiscUtilsTest {


   @Test
   public void exceptionToStringIsNotNull() {
        String result = null;
        Exception e = new Exception("Test Exception");
        result = MiscUtils.exceptionToString(e); 
        assertNotNull(result); 
  }


   @Test
   public void stringSizeLengthFileIsNotNull() {
        String result = null;
        long l = 1L;
        result = MiscUtils.getStringSizeLengthFile(l); 
        assertNotNull(result);
        System.out.println(result); 
  }



}



