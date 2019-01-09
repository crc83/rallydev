package org.sbelei.rally.domain.constants;

import static org.junit.Assert.*;

import org.junit.Test;


public class StoryStateTest {

	@Test
	public void testToString() throws Exception {
		//we should have dashes instead of underscores
		assertEquals("In-Progress", StoryState.In_Progress.toString());		
	}
}
