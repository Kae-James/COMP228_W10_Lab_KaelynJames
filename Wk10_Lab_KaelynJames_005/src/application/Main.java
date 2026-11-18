package application;
	
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		// Refer to the Main page of W10 example
		EmploymentFormDao formDao = new EmploymentFormDao();

		// Create title
		primaryStage.setTitle("Employment Application");

		// Set GridPane
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		int row = 0;

		// Create big title
		Text sceneTitle = new Text("Employment Application");
		grid.add(sceneTitle, 0, row++, 2, 1);

		// Personal Information
		Text personalTitle = new Text("Personal Information");
		personalTitle.setStyle("-fx-font-weight: bold");
		grid.add(personalTitle, 0, row++, 2, 1);

		grid.add(new Label("Full Name:"), 0, row);
		TextField fullNameField = new TextField();
		grid.add(fullNameField, 1, row++);

		grid.add(new Label("Current Address:"), 0, row);
		TextField addressField = new TextField();
		grid.add(addressField, 1, row++);

		grid.add(new Label("Contact Number:"), 0, row);
		TextField contactField = new TextField();
		grid.add(contactField, 1, row++);

		grid.add(new Label("Email Address:"), 0, row);
		TextField emailField = new TextField();
		grid.add(emailField, 1, row++);

		grid.add(new Label("Highest Educational Attainment:"), 0, row);
		TextField educationField = new TextField();
		grid.add(educationField, 1, row++);

		grid.add(new Label("Gender:"), 0, row);
		ToggleGroup genderGroup = new ToggleGroup();
		RadioButton maleRadio = new RadioButton("Male");
		RadioButton femaleRadio = new RadioButton("Female");
		RadioButton otherRadio = new RadioButton("Other");
		maleRadio.setToggleGroup(genderGroup);
		femaleRadio.setToggleGroup(genderGroup);
		otherRadio.setToggleGroup(genderGroup);
		grid.add(new HBox(10, maleRadio, femaleRadio, otherRadio), 1, row++);

		// Employment Eligibility
		Text eligibilityTitle = new Text("Employment Eligibility");
		eligibilityTitle.setStyle("-fx-font-weight: bold");
		grid.add(eligibilityTitle, 0, row++, 2, 1);

		grid.add(new Label("Date Available:"), 0, row);
		TextField dateAvailableField = new TextField();
		dateAvailableField.setPromptText("yyyy-mm-dd");
		grid.add(dateAvailableField, 1, row++);

		grid.add(new Label("Desired Position:"), 0, row);
		TextField positionField = new TextField();
		grid.add(positionField, 1, row++);

		grid.add(new Label("Desired Salary:"), 0, row);
		TextField salaryField = new TextField();
		grid.add(salaryField, 1, row++);

		grid.add(new Label("Legally authorized to work in the country?"), 0, row);
		ToggleGroup authorizedGroup = new ToggleGroup();
		RadioButton authorizedYes = new RadioButton("Yes");
		RadioButton authorizedNo = new RadioButton("No");
		authorizedYes.setToggleGroup(authorizedGroup);
		authorizedNo.setToggleGroup(authorizedGroup);
		grid.add(new HBox(10, authorizedYes, authorizedNo), 1, row++);

		grid.add(new Label("Relatives working for our company?"), 0, row);
		ToggleGroup relativesGroup = new ToggleGroup();
		RadioButton relativesYes = new RadioButton("Yes");
		RadioButton relativesNo = new RadioButton("No");
		relativesYes.setToggleGroup(relativesGroup);
		relativesNo.setToggleGroup(relativesGroup);
		grid.add(new HBox(10, relativesYes, relativesNo), 1, row++);

		grid.add(new Label("If yes, please explain further:"), 0, row);
		TextField relativesExplainField = new TextField();
		grid.add(relativesExplainField, 1, row++);
		
		// Agreement text 
		Label agreementLabel = new Label("By submitting this application, you agree to adhere to company policies "
		        + "and provide accurate information throughout the employment process.");
		agreementLabel.setWrapText(true);
		agreementLabel.setMaxWidth(300);
		grid.add(agreementLabel, 0, row);
		
		// Submit button
		Button submitButton = new Button("Submit");
		HBox buttonBox = new HBox(10, submitButton);
		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
		grid.add(buttonBox, 1, row++);

		submitButton.setOnAction(actionEvent -> {
			String fullName = fullNameField.getText().trim();
			String address = addressField.getText().trim();
			String contact = contactField.getText().trim();
			String email = emailField.getText().trim();
			String education = educationField.getText().trim();
			RadioButton genderSelected = (RadioButton) genderGroup.getSelectedToggle();
			String dateAvailable = dateAvailableField.getText().trim();
			String position = positionField.getText().trim();
			String salaryText = salaryField.getText().trim();
			String relativesExplain = relativesExplainField.getText().trim();

			if (StringPool.BLANK.equals(fullName) || StringPool.BLANK.equals(address)
					|| StringPool.BLANK.equals(contact) || StringPool.BLANK.equals(email)
					|| StringPool.BLANK.equals(education) || genderSelected == null
					|| StringPool.BLANK.equals(dateAvailable) || StringPool.BLANK.equals(position)
					|| StringPool.BLANK.equals(salaryText)
					|| authorizedGroup.getSelectedToggle() == null
					|| relativesGroup.getSelectedToggle() == null) {
				this.alert("Error", "Please complete all fields!", AlertType.ERROR);
				return;
			}

			if (relativesYes.isSelected() && StringPool.BLANK.equals(relativesExplain)) {
				this.alert("Error", "Please explain your relatives working for the company.", AlertType.ERROR);
				return;
			}

			try {
				java.sql.Date.valueOf(dateAvailable); // checks the date's format
			} catch (IllegalArgumentException ex) {
				this.alert("Error", "Date Available must be in yyyy-mm-dd format.", AlertType.ERROR);
				return;
			}

			try {
				EmploymentForm form = this.createFormObject(fullName, address, contact, email, education,
						genderSelected.getText(), dateAvailable, position, salaryText,
						authorizedYes.isSelected(), relativesYes.isSelected(), relativesExplain);
				int formId = formDao.saveEmploymentForm(form);
				if (formId > 0) {
					this.alert("Submit", "Application submitted successfully!", AlertType.INFORMATION);
				} else {
					this.alert("Error", "Failed!", AlertType.ERROR);
				}
			} catch (SQLException ex) {
				System.out.println("Exception occured");
				System.out.println(ex.getMessage());
				System.out.println(ex.getErrorCode());
				System.out.println(ex.getSQLState());
				this.alert("Error", "Database error: " + ex.getMessage(), AlertType.ERROR);
			}
		});

		Scene scene = new Scene(grid, 650, 650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public EmploymentForm createFormObject(String fullName, String address, String contact, String email,
			String education, String gender, String dateAvailable, String position, String salary,
			boolean authorized, boolean hasRelatives, String relativesExplain) {
		EmploymentForm form = new EmploymentForm();
		form.setFullName(fullName);
		form.setCurrentAddress(address);
		form.setContactNumber(contact);
		form.setEmailAddress(email);
		form.setHighestEducationalAttainment(education);
		form.setGender(gender);
		form.setDateAvailable(dateAvailable);
		form.setDesiredPosition(position);
		form.setDesiredSalary(salary);
		form.setAuthorizedToWork(authorized);
		form.setRelativesAtCompany(hasRelatives);
		form.setRelativesExplanation(relativesExplain);

		return form;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}