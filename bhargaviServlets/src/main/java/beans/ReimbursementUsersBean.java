package beans;

public class ReimbursementUsersBean {

	private int ersUserId;
	private String ersUsername;
	private String ersPassword;
	private String ersFirstName;
	private String ersLastName;
	private String userEmail;

	private int userRoleId;

	public ReimbursementUsersBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReimbursementUsersBean(int ersUserId, String ersUsername, String ersPassword, String ersFirstName,
			String ersLastName, String userEmail, int userRoleId) {
		super();
		this.ersUserId = ersUserId;
		this.ersUsername = ersUsername;
		this.ersPassword = ersPassword;
		this.ersFirstName = ersFirstName;
		this.ersLastName = ersLastName;
		this.userEmail = userEmail;
		this.userRoleId = userRoleId;
	}

	public int getErsUserId() {
		return ersUserId;
	}

	public void setErsUserId(int ersUserId) {
		this.ersUserId = ersUserId;
	}

	public String getErsUsername() {
		return ersUsername;
	}

	public void setErsUsername(String ersUsername) {
		this.ersUsername = ersUsername;
	}

	public String getErsPassword() {
		return ersPassword;
	}

	public void setErsPassword(String ersPassword) {
		this.ersPassword = ersPassword;
	}

	public String getErsFirstName() {
		return ersFirstName;
	}

	public void setErsFirstName(String ersFirstName) {
		this.ersFirstName = ersFirstName;
	}

	public String getErsLastName() {
		return ersLastName;
	}

	public void setErsLastName(String ersLastName) {
		this.ersLastName = ersLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Override
	public String toString() {
		return "ReimbursementUsersBean [ersUserId=" + ersUserId + ", ersUsername=" + ersUsername + ", ersPassword="
				+ ersPassword + ", ersFirstName=" + ersFirstName + ", ersLastName=" + ersLastName + ", userEmail="
				+ userEmail + ", userRoleId=" + userRoleId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ersFirstName == null) ? 0 : ersFirstName.hashCode());
		result = prime * result + ((ersLastName == null) ? 0 : ersLastName.hashCode());
		result = prime * result + ((ersPassword == null) ? 0 : ersPassword.hashCode());
		result = prime * result + ersUserId;
		result = prime * result + ((ersUsername == null) ? 0 : ersUsername.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + userRoleId;
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
		ReimbursementUsersBean other = (ReimbursementUsersBean) obj;
		if (ersFirstName == null) {
			if (other.ersFirstName != null)
				return false;
		} else if (!ersFirstName.equals(other.ersFirstName))
			return false;
		if (ersLastName == null) {
			if (other.ersLastName != null)
				return false;
		} else if (!ersLastName.equals(other.ersLastName))
			return false;
		if (ersPassword == null) {
			if (other.ersPassword != null)
				return false;
		} else if (!ersPassword.equals(other.ersPassword))
			return false;
		if (ersUserId != other.ersUserId)
			return false;
		if (ersUsername == null) {
			if (other.ersUsername != null)
				return false;
		} else if (!ersUsername.equals(other.ersUsername))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userRoleId != other.userRoleId)
			return false;
		return true;
	}

}
