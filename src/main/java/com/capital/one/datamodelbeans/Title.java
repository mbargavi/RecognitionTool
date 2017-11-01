package com.capital.one.datamodelbeans;

import org.springframework.stereotype.Component;

@Component
public class Title {
	
	private int titleId;
	private String titleName;
	private int creditThreshold;
	
	
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public int getCreditThreshold() {
		return creditThreshold;
	}
	public void setCreditThreshold(int creditThreshold) {
		this.creditThreshold = creditThreshold;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditThreshold;
		result = prime * result + titleId;
		result = prime * result + ((titleName == null) ? 0 : titleName.hashCode());
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
		Title other = (Title) obj;
		if (creditThreshold != other.creditThreshold)
			return false;
		if (titleId != other.titleId)
			return false;
		if (titleName == null) {
			if (other.titleName != null)
				return false;
		} else if (!titleName.equals(other.titleName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Title [titleId=" + titleId + ", titleName=" + titleName + ", creditThreshold=" + creditThreshold + "]";
	}
	
	

}
