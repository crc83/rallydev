package org.sbelei.rally.domain.constants;

/**
 * Enum with possible defect states
 * 
 * @author sbelei
 */
public enum DefectState {
	Submitted,
	Open,
	Fixed,
	Closed,
	Reopened,
	CS_Tracking,
	T2_Verify;
	
	@Override
	public String toString(){
		return name().replaceAll("_", " ");
	}
}
