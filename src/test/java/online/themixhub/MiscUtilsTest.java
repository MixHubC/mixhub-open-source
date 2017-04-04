package online.themixhub;

import static org.junit.Assert.assertNotNull;

import online.themixhub.demo.utils.MiscUtils;
import org.junit.Test;

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


