package org.sbelei.rally.domain;

public class BasicEntity {
	
    public String name;
    public String id;
    public String ref;

    @Override
    public String toString() {
        return "\n entity id=" + id + "\tname=" + name + "";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BasicEntity)) {
			return false;
		}
		BasicEntity other = (BasicEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
    
    
}
