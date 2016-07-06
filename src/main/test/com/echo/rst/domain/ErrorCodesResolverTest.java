package com.echo.rst.domain;

import com.echo.rst.domain.ErrorCodesResolver;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by echo on 16-7-6.
 */
public class ErrorCodesResolverTest {
	private static ErrorCodesResolver ecr;

	@BeforeClass
	public static void beforeClass() {
		ecr = new ErrorCodesResolver();
	}

	@Test
	public void testGetValue() {
		String value = ecr.getValue("hello");
		Assert.assertEquals("你好！", value);
	}
}
