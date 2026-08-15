package com.dihadi.view.signIn;

import java.util.Locale;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/** Worker personal-details form used when creating a DIHADI worker profile. */
public class WorkerProfile {
    private static final String[] SLIDES = {
        "/assets/images/worker 1 (2).jpeg", "/assets/images/worker 2.jpeg",
        "/assets/images/worker 5.jpeg", "/assets/images/generalLabour.jpeg",
        "/assets/images/sitesuperviser.jpeg"
    };

    private ImageView slideImage;
    private Timeline slider;
    private int slideIndex;

    public Scene getProfileScene(Runnable backAction) {
        stopSlider();

        VBox formContent = new VBox(22);
        formContent.setAlignment(Pos.TOP_CENTER);
        formContent.setPadding(new Insets(48, 56, 48, 56));
        formContent.setMaxWidth(620);

        ImageView logo = logoImage();
        Label brand = label("DIHADI", "-fx-font-size: 28px; -fx-font-weight: 800; -fx-text-fill: #415ba4; -fx-letter-spacing: 1px;");
        Label slogan = label("Mera Haq ~ Meri Dihadi", "-fx-font-size: 18px; -fx-font-style: italic; -fx-text-fill: #685c52;");
        VBox identity = new VBox(3, logo, brand, slogan);
        identity.setAlignment(Pos.CENTER);

        Label welcome = label("Welcome to DIHADI", "-fx-font-size: 29px; -fx-font-weight: 800; -fx-text-fill: #1e1b15;");
        Label accountText = label("Sign In or Create a New Account", "-fx-font-size: 17px; -fx-text-fill: #4c4637;");
        Label instruction = label("Go ahead, enter your personal details to proceed ahead.", "-fx-font-size: 14px; -fx-text-fill: #685c52;");
        VBox intro = new VBox(7, welcome, accountText, instruction);
        intro.setAlignment(Pos.CENTER);

        Button back = textButton("←  PERSONAL DETAILS");
        back.setOnAction(event -> { if (backAction != null) backAction.run(); });

        StackPane avatar = new StackPane(label("♙", "-fx-font-size: 42px; -fx-text-fill: #4d4635;"));
        avatar.setPrefSize(90, 90);
        avatar.setStyle("-fx-background-color: #eee7dc; -fx-background-radius: 45px; -fx-border-color: #cfc6b2; -fx-border-radius: 45px; -fx-border-width: 2px;");
        Button changePhoto = subtleButton("Change Photo");
        changePhoto.setOnAction(event -> System.out.println("Change Photo clicked"));
        HBox photoRow = new HBox(20, avatar, changePhoto);
        photoRow.setAlignment(Pos.CENTER_LEFT);

        TextField firstName = field("e.g. Ram");
        TextField middleName = field("e.g. D");
        TextField lastName = field("e.g. Kumar");
        ComboBox<String> gender = combo("Select", "पुरुष (Male)", "महिला (Female)", "अन्य (Other)");
        TextField dob = field("DD/MM/YYYY");
        TextField mobile = field("Enter 10-digit mobile number");
        TextField alternateMobile = field("Enter alternate mobile number");
        TextField email = field("e.g. xyz@gmail.com");
        ComboBox<String> education = combo("Select", "5th Pass Or Below", "8th Pass", "10th Pass", "12th Pass", "Graduate");
        ComboBox<String> experience = combo("Select", "0-1 Years", "1-3 Years", "3-5 Years", "5+ Years");

        VBox firstBox = inputBox("First name", true, firstName);
        VBox middleBox = inputBox("Middle name", false, middleName);
        VBox lastBox = inputBox("Last name", false, lastName);
        VBox genderBox = inputBox("Gender", true, gender);
        VBox dobBox = inputBox("Date of birth", true, dob);
        VBox mobileBox = mobileBox("Mobile number", true, mobile);
        VBox alternateBox = inputBox("Alternate mobile number", false, alternateMobile);
        VBox emailBox = inputBox("Email", false, email);
        VBox educationBox = inputBox("Education / Qualification", true, education);
        VBox experienceBox = inputBox("Experience", true, experience);

        HBox nameRow = new HBox(18, middleBox, lastBox);
        HBox detailsRow = new HBox(18, genderBox, dobBox);
        HBox qualificationRow = new HBox(18, educationBox, experienceBox);
        for (VBox box : new VBox[]{middleBox, lastBox, genderBox, dobBox, educationBox, experienceBox}) HBox.setHgrow(box, Priority.ALWAYS);

        TextField dailyWages = field("Enter daily wage");
        Label expectedDaily = label("₹0", "-fx-font-size: 18px; -fx-font-weight: 800; -fx-text-fill: #e91e63;");
        Label expectedMonthly = label("₹0", "-fx-font-size: 18px; -fx-font-weight: 800; -fx-text-fill: #7e7665;");
        ChangeListener<String> wagesListener = (observable, oldValue, value) -> updateWages(value, expectedDaily, expectedMonthly);
        dailyWages.textProperty().addListener(wagesListener);
        VBox wages = new VBox(10,
            requiredLabel("Daily wages", true), dailyWages,
            new HBox(8, label("Your expected daily wages", "-fx-font-size: 14px; -fx-text-fill: #685c52;"), expectedDaily),
            new HBox(8, label("Your expected monthly wages", "-fx-font-size: 14px; -fx-text-fill: #685c52;"), expectedMonthly));
        wages.setPadding(new Insets(20));
        wages.setStyle("-fx-background-color: #f4ede2; -fx-background-radius: 12px; -fx-border-color: #cfc6b2; -fx-border-radius: 12px;");

        CheckBox consent = new CheckBox("I authorise DIHADI to send notifications via SMS, Email, RCS and others as per the Terms of Service and Privacy Policy.");
        consent.setWrapText(true);
        consent.setStyle("-fx-font-size: 13px; -fx-text-fill: #4c4637;");
        Button continueButton = primaryButton("Continue");
        continueButton.setMaxWidth(Double.MAX_VALUE);
        Label error = label("", "-fx-font-size: 13px; -fx-text-fill: #ba1a1a;");
        continueButton.setOnAction(event -> validateAndContinue(firstName, gender, dob, mobile, education, experience, dailyWages, consent, error));
        Label loginText = label("Already Having Account?", "-fx-font-size: 14px; -fx-text-fill: #4c4637;");
        Button login = textButton("Login");
        login.setOnAction(event -> System.out.println("Login clicked"));
        HBox loginRow = new HBox(4, loginText, login);
        loginRow.setAlignment(Pos.CENTER);

        VBox card = new VBox(18, back, photoRow, firstBox, nameRow, detailsRow, mobileBox, alternateBox, emailBox, qualificationRow, wages, consent, error, continueButton, loginRow);
        card.setPadding(new Insets(32));
        card.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 16px; -fx-border-color: #e9e2d7; -fx-border-radius: 16px; -fx-effect: dropshadow(gaussian, rgba(58,48,39,0.08), 24, 0, 0, 4px);");
        formContent.getChildren().addAll(identity, intro, card);

        ScrollPane formScroll = new ScrollPane(formContent);
        formScroll.setFitToWidth(true);
        formScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        formScroll.setStyle("-fx-background: #f2f2f2; -fx-background-color: #f2f2f2; -fx-border-width: 0;");

        StackPane slideshow = createSlideshow();
        BorderPane root = new BorderPane();
        root.setLeft(formScroll);
        root.setCenter(slideshow);
        BorderPane.setAlignment(formScroll, Pos.CENTER);
        return new Scene(root, 1400, 820);
    }

    private VBox inputBox(String title, boolean required, javafx.scene.Node input) {
        VBox box = new VBox(7, requiredLabel(title, required), input);
        VBox.setVgrow(input, Priority.NEVER);
        return box;
    }

    private VBox mobileBox(String title, boolean required, TextField mobile) {
        Label prefix = label("+91", "-fx-font-size: 14px; -fx-text-fill: #1e1b15;");
        prefix.setPadding(new Insets(11, 14, 11, 14));
        prefix.setStyle("-fx-background-color: #eee7dc; -fx-background-radius: 8px 0 0 8px; -fx-border-color: #cfc6b2; -fx-border-width: 0 1px 0 0;");
        mobile.setStyle(fieldStyle() + "-fx-background-radius: 0 8px 8px 0;");
        HBox row = new HBox(prefix, mobile); HBox.setHgrow(mobile, Priority.ALWAYS);
        return inputBox(title, required, row);
    }

    private Label requiredLabel(String text, boolean required) {
        return label(text + (required ? "  *" : ""), "-fx-font-size: 13px; -fx-font-weight: 700; -fx-text-fill: " + (required ? "#1e1b15" : "#4c4637") + ";");
    }

    private StackPane createSlideshow() {
        // All available worker photos are 1376 x 768 (about 16:9).
        // Keeping this same ratio prevents the slideshow from stretching faces.
        slideImage = image(SLIDES[0], 620, 349);
        slideImage.setPreserveRatio(true);
        StackPane frame = new StackPane(slideImage);
        frame.setPrefSize(620, 349);
        frame.setMaxSize(620, 349);
        frame.setStyle("-fx-background-color: white; -fx-background-radius: 18px; -fx-border-color: #cfc6b2; -fx-border-radius: 18px; -fx-effect: dropshadow(gaussian, rgba(58,48,39,0.12), 30, 0, 0, 8px);");
        Label heading = label("Build your future with DIHADI", "-fx-font-size: 22px; -fx-font-weight: 800; -fx-text-fill: #3a3027;");
        Label message = label("Find dignified work, trusted opportunities, and fair daily wages.", "-fx-font-size: 15px; -fx-text-fill: #4d4635;");
        message.setWrapText(true); message.setMaxWidth(480);
        VBox content = new VBox(18, frame, heading, message); content.setAlignment(Pos.CENTER);
        StackPane panel = new StackPane(content);
        panel.setPadding(new Insets(50));
        panel.setBackground(new Background(new BackgroundFill(Color.web("#ebebeb"), CornerRadii.EMPTY, Insets.EMPTY)));
        startSlider();
        return panel;
    }

    private void validateAndContinue(TextField firstName, ComboBox<String> gender, TextField dob, TextField mobile, ComboBox<String> education, ComboBox<String> experience, TextField wages, CheckBox consent, Label error) {
        if (firstName.getText().trim().isEmpty() || gender.getValue() == null || dob.getText().trim().isEmpty() || mobile.getText().trim().isEmpty() || education.getValue() == null || experience.getValue() == null || wages.getText().trim().isEmpty()) {
            error.setText("Please complete all required fields."); return;
        }
        if (!consent.isSelected()) { error.setText("Please accept the notification terms to continue."); return; }
        error.setStyle("-fx-font-size: 13px; -fx-text-fill: #2e7d32;");
        error.setText("Profile details saved successfully.");
        System.out.println("Worker profile saved for " + firstName.getText().trim());
    }

    private void updateWages(String value, Label daily, Label monthly) {
        try { double wage = Double.parseDouble(value.trim()); daily.setText("₹" + formatWage(wage)); monthly.setText("₹" + formatWage(wage * 30)); }
        catch (NumberFormatException exception) { daily.setText("₹0"); monthly.setText("₹0"); }
    }
    private String formatWage(double wage) { return wage == Math.rint(wage) ? String.format(Locale.US, "%.0f", wage) : String.format(Locale.US, "%.2f", wage); }
    private TextField field(String prompt) { TextField field = new TextField(); field.setPromptText(prompt); field.setStyle(fieldStyle()); field.setPrefHeight(42); return field; }
    private String fieldStyle() { return "-fx-background-color: #faf3e8; -fx-background-radius: 8px; -fx-border-color: transparent; -fx-font-size: 14px; -fx-padding: 10px 12px;"; }
    private ComboBox<String> combo(String prompt, String... values) { ComboBox<String> box = new ComboBox<>(); box.setPromptText(prompt); box.getItems().addAll(values); box.setMaxWidth(Double.MAX_VALUE); box.setPrefHeight(42); box.setStyle("-fx-background-color: #faf3e8; -fx-background-radius: 8px; -fx-font-size: 14px;"); return box; }
    private Label label(String text, String style) { Label label = new Label(text); label.setStyle("-fx-font-family: 'Segoe UI', sans-serif; " + style); return label; }
    private Button primaryButton(String text) { Button button = new Button(text); button.setStyle("-fx-background-color: #d4af37; -fx-background-radius: 999px; -fx-text-fill: #231b00; -fx-font-size: 16px; -fx-font-weight: 800; -fx-padding: 13px 24px; -fx-cursor: hand;"); return button; }
    private Button subtleButton(String text) { Button button = new Button(text); button.setStyle("-fx-background-color: #e9e2d7; -fx-background-radius: 999px; -fx-text-fill: #1e1b15; -fx-font-size: 13px; -fx-font-weight: 700; -fx-padding: 10px 22px; -fx-cursor: hand;"); return button; }
    private Button textButton(String text) { Button button = new Button(text); button.setStyle("-fx-background-color: transparent; -fx-padding: 2px 0; -fx-text-fill: #735c00; -fx-font-size: 13px; -fx-font-weight: 800; -fx-cursor: hand;"); return button; }
    private ImageView logoImage() { ImageView logo = image("/assets/logo/dihadi logo.jpeg", 62, 62); logo.setViewport(new Rectangle2D(380, 0, 840, 840)); logo.setPreserveRatio(true); return logo; }
    private ImageView image(String path, double width, double height) { ImageView view = new ImageView(loadImage(path)); view.setFitWidth(width); view.setFitHeight(height); view.setPreserveRatio(false); view.setSmooth(true); return view; }
    private Image loadImage(String path) { var resource = getClass().getResource(path); return resource == null ? null : new Image(resource.toExternalForm()); }
    private void startSlider() { slider = new Timeline(new KeyFrame(Duration.seconds(5), event -> { slideIndex = (slideIndex + 1) % SLIDES.length; slideImage.setImage(loadImage(SLIDES[slideIndex])); })); slider.setCycleCount(Timeline.INDEFINITE); slider.play(); }
    private void stopSlider() { if (slider != null) slider.stop(); }
}
