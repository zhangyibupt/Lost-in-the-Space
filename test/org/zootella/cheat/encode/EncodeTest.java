package org.zootella.cheat.encode;

import static org.junit.Assert.*;

import org.junit.Test;
import org.zootella.cheat.data.Data;
import org.zootella.cheat.data.Encode;


public class EncodeTest {
	
	@Test
	public void test() throws Exception {
		
		test("");
		test("a");
		test("ab");
		test("[");
		test("]");
		test("\0");
		test("\t");
		test("a[b]c\r\n");
		test("[hello]\0\t\r\n");

		test(Data.random(16));
	}

	public void test(String original) throws Exception {
			
		String encoded = Encode.box(new Data(original));
		String decoded = Encode.unbox(encoded).toString();
		assertTrue(original.equals(decoded));
	}

	public void test(Data original) throws Exception {
			
		String encoded = Encode.box(original);
		Data decoded = Encode.unbox(encoded);
		assertTrue(original.equals(decoded));
	}
}
