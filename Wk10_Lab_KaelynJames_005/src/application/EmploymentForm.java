package application;

// Creating a class to model the entity given in the question
public class EmploymentForm {

	// All 12 fields
	private String fullName;
	private String currentAddress;
	private String contactNumber;
	private String emailAddress;
	private String highestEducationalAttainment;
	private String gender;
	private String dateAvailable;
	private String desiredPosition;
	private String desiredSalary;
	private boolean authorizedToWork;
	private boolean relativesAtCompany;
	private String relativesExplanation;
	
	// Getters and setters for all fields
	public String getFullName() {
		return fullName;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getHighestEducationalAttainment() {
		return highestEducationalAttainment;
	}
	public String getGender() {
		return gender;
	}
	public String getDateAvailable() {
		return dateAvailable;
	}
	public String getDesiredPosition() {
		return desiredPosition;
	}
	public String getDesiredSalary() {
		return desiredSalary;
	}
	public boolean isAuthorizedToWork() {
		return authorizedToWork;
	}
	public boolean isRelativesAtCompany() {
		return relativesAtCompany;
	}
	public String getRelativesExplanation() {
		return relativesExplanation;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public void setHighestEducationalAttainment(String highestEducationalAttainment) {
		this.highestEducationalAttainment = highestEducationalAttainment;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDateAvailable(String dateAvailable) {
		this.dateAvailable = dateAvailable;
	}
	public void setDesiredPosition(String desiredPosition) {
		this.desiredPosition = desiredPosition;
	}
	public void setDesiredSalary(String desiredSalary) {
		this.desiredSalary = desiredSalary;
	}
	public void setAuthorizedToWork(boolean authorizedToWork) {
		this.authorizedToWork = authorizedToWork;
	}
	public void setRelativesAtCompany(boolean relativesAtCompany) {
		this.relativesAtCompany = relativesAtCompany;
	}
	public void setRelativesExplanation(String relativesExplanation) {
		this.relativesExplanation = relativesExplanation;
	}
}
