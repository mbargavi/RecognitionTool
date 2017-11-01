package com.capital.one.datamodelbeans;

import org.springframework.stereotype.Component;

@Component
public class Credit {
	
	private int creditId;
	private String creditName;
	
	
	public int getCreditId() {
		return creditId;
	}
	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditId;
		result = prime * result + ((creditName == null) ? 0 : creditName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credit other = (Credit) obj;
		if (creditId != other.creditId)
			return false;
		if (creditName == null) {
			if (other.creditName != null)
				return false;
		} else if (!creditName.equals(other.creditName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Credit [creditId=" + creditId + ", creditName=" + creditName + "]";
	}
	
	

}
