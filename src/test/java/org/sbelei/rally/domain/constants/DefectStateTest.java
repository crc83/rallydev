package org.sbelei.rally.domain.constants;

import static org.junit.Assert.*;

import org.junit.Test;


public class DefectStateTest {

	@Test
	public void testToString() throws Exception {
		//underscores should be translated to spaces
		assertEquals("CS Tracking", DefectState.CS_Tracking.toString());
	}
}
