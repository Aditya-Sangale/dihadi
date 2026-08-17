package com.dihadi.view.worker.mason;

import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

/** Mason jobs marketplace based on the supplied Mason Job Roles reference. */
public class MasonJobRole {
    private static final String[] TITLES = { "Shuttering", "Brick Mason", "Tiles Mason", "Bar Bender",
            "Garters/Columns Mason", "Cement Concrete Mason", "Brick Mason", "Marbles Mason" };
    private static final String[] LOCATIONS = { "Madurai South, TAMIL NADU", "Shajapur, MADHYA PRADESH",
            "New Delhi, DELHI", "Anekal, KARNATAKA", "Sriperumbudur, TAMIL NADU", "Kolkata East, WEST BENGAL",
            "Ahmadabad City, GUJARAT", "Pune, MAHARASHTRA" };
    private static final String[] WAGES = { "₹1200", "₹600", "₹1000", "₹700", "₹800–900", "₹600", "₹650", "₹1100" };
    private Timeline slider;

    public Scene getMasonJobRoleScene(Runnable back) {
        VBox content = new VBox(44, hero(), filters(), jobs());
        content.setMaxWidth(1240);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(42, 36, 70, 36));
        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color:transparent;-fx-border-width:0;");
        BorderPane page = new BorderPane(scroll);
        page.setTop(header(back));
        page.setStyle("-fx-background-color:#fff8f0;");
        return new Scene(page, 1400, 780);
    }

    private BorderPane header(Runnable back) {
        Label brand = label("DIHADI",
                "-fx-font-family:Georgia;-fx-font-size:30px;-fx-font-weight:800;-fx-text-fill:#735c00;");
        Button search = new Button("⌕"), bell = new Button("◌"), user = new Button("◉");
        for (Button b : new Button[] { search, bell, user })
            b.setStyle("-fx-background-color:transparent;-fx-font-size:18px;-fx-text-fill:#735c00;-fx-cursor:hand;");
        user.setOnAction(e -> {
            if (back != null)
                back.run();
        });
        BorderPane bar = new BorderPane();
        bar.setLeft(brand);
        bar.setRight(new HBox(14, search, bell, user));
        bar.setPadding(new Insets(22, 58, 18, 58));
        bar.setStyle("-fx-background-color:#fff8f0;-fx-border-color:#d0c5af;-fx-border-width:0 0 1px 0;");
        return bar;
    }

    private VBox hero() {
        Label heading = label("Mason Job Roles",
                "-fx-font-family:Georgia;-fx-font-size:34px;-fx-font-weight:700;-fx-text-fill:#1f1b13;");
        ImageView image = picture(1, 560, 350);
        StackPane slideshow = new StackPane(image);
        slideshow.setPrefSize(560, 350);
        slideshow.setStyle("-fx-background-color:#eae1d4;-fx-background-radius:18px;");
        startSlider(image);
        Label quote = label(
                "\"Shaping the world one brick at a time. We honor the enduring craftsmanship and unyielding strength of our masons who build the structures of tomorrow.\"",
                "-fx-font-family:Georgia;-fx-font-size:22px;-fx-font-style:italic;-fx-text-fill:#4d4635;-fx-line-spacing:5px;");
        quote.setWrapText(true);
        quote.setMaxWidth(500);
        VBox words = new VBox(quote);
        words.setAlignment(Pos.CENTER_LEFT);
        words.setPadding(new Insets(20, 0, 20, 28));
        words.setStyle("-fx-border-color:#d4af37;-fx-border-width:0 0 0 4px;");
        HBox split = new HBox(66, slideshow, words);
        split.setAlignment(Pos.CENTER);
        return new VBox(30, heading, split);
    }

    private VBox filters() {
        Label title = label("Find a Suitable Job Role for you",
                "-fx-font-size:20px;-fx-font-weight:700;-fx-text-fill:#1f1b13;");
        ComboBox<String> country = select("Select Country", "India"),
                state = select("Select State", "Delhi", "Bihar", "Madhya Pradesh", "Tamil Nadu", "Maharashtra"),
                city = select("Select City");
        TextField pin = new TextField();
        pin.setPromptText("Select Pincode");
        pin.setStyle(input());
        HBox row = new HBox(16, country, state, city, pin);
        for (Node n : row.getChildren()) {
            if (n instanceof Region r) {
                r.setPrefWidth(260);
                HBox.setHgrow(r, Priority.ALWAYS);
            }
        }
        VBox box = new VBox(18, title, row);
        box.setPadding(new Insets(26));
        box.setStyle(
                "-fx-background-color:#fbf3e5;-fx-background-radius:18px;-fx-effect:dropshadow(gaussian,rgba(58,48,39,.07),12,0,0,4px);");
        return box;
    }

    private FlowPane jobs() {
        FlowPane grid = new FlowPane(24, 24);
        grid.setAlignment(Pos.CENTER);
        grid.setPrefWrapLength(1160);
        for (int i = 0; i < TITLES.length; i++)
            grid.getChildren().add(job(i));
        return grid;
    }

    private VBox job(int i) {
        ImageView photo = picture((i % 15) + 1, 540, 190);
        Label title = label(TITLES[i], "-fx-font-size:20px;-fx-font-weight:700;-fx-text-fill:#1f1b13;");
        Label hindi = label("Mason work opportunity", "-fx-font-size:15px;-fx-text-fill:#4d4635;");
        Label location = label("⌖  " + LOCATIONS[i],
                "-fx-font-size:12px;-fx-font-weight:700;-fx-text-fill:#685c52;-fx-background-color:#f1dfd2;-fx-background-radius:999px;-fx-padding:7px 10px;");
        HBox top = new HBox(12, new VBox(5, title, hindi), location);
        top.setAlignment(Pos.TOP_LEFT);
        Label wageLabel = label("DAILY WAGE", "-fx-font-size:12px;-fx-font-weight:700;-fx-text-fill:#4d4635;");
        Label wage = label(WAGES[i],
                "-fx-font-family:Georgia;-fx-font-size:25px;-fx-font-weight:700;-fx-text-fill:#d4af37;");
        Button apply = new Button("APPLY");
        apply.setStyle(
                "-fx-background-color:#d4af37;-fx-background-radius:999px;-fx-text-fill:#fff8f0;-fx-font-weight:800;-fx-padding:11px 25px;-fx-cursor:hand;");
        Region gap = new Region();
        HBox.setHgrow(gap, Priority.ALWAYS);
        HBox bottom = new HBox(new VBox(3, wageLabel, wage), gap, apply);
        bottom.setAlignment(Pos.CENTER_LEFT);
        VBox card = new VBox(18, photo, top, divider(), bottom);
        card.setPrefSize(560, 410);
        card.setPadding(new Insets(0, 26, 22, 26));
        card.setStyle(
                "-fx-background-color:#fffdf9;-fx-background-radius:15px;-fx-border-color:#d0c5af;-fx-border-radius:15px;-fx-effect:dropshadow(gaussian,rgba(58,48,39,.08),16,0,0,5px);");
        return card;
    }

    private void startSlider(ImageView image) {
        if (slider != null)
            slider.stop();
        final int[] index = { 1 };
        slider = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            index[0] = index[0] % 15 + 1;
            image.setImage(load(String.format("/assets/images/worker/mason/skill-%02d.jpg", index[0])));
        }));
        slider.setCycleCount(Timeline.INDEFINITE);
        slider.play();
    }

    private ComboBox<String> select(String prompt, String... values) {
        ComboBox<String> c = new ComboBox<>();
        c.setPromptText(prompt);
        c.getItems().addAll(values);
        c.setMaxWidth(Double.MAX_VALUE);
        c.setStyle(input());
        return c;
    }

    private String input() {
        return "-fx-background-color:#eae1d4;-fx-background-radius:10px;-fx-border-color:transparent;-fx-font-size:15px;-fx-padding:10px 14px;-fx-pref-height:46px;";
    }

    private Region divider() {
        Region r = new Region();
        r.setPrefHeight(1);
        r.setStyle("-fx-background-color:#d0c5af;");
        return r;
    }

    private Button nav(String t) {
        Button b = new Button(t);
        b.setStyle("-fx-background-color:transparent;-fx-font-size:15px;-fx-text-fill:#4d4635;-fx-cursor:hand;");
        return b;
    }

    private Label label(String t, String s) {
        Label l = new Label(t);
        l.setStyle(s);
        return l;
    }

    private ImageView picture(int n, double w, double h) {
        ImageView v = new ImageView(load(String.format("/assets/images/worker/mason/skill-%02d.jpg", n)));
        v.setFitWidth(w);
        v.setFitHeight(h);
        v.setPreserveRatio(false);
        return v;
    }

    private Image load(String p) {
        var r = getClass().getResource(p);
        return r == null ? null : new Image(r.toExternalForm());
    }
}
