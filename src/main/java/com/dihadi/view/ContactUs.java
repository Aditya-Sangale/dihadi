package com.dihadi.view;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

/** Contact page matching the DIHADI business visual system. */
public class ContactUs {
    private Runnable home, business, worker, about;

    public Scene getContactScene(Runnable home, Runnable business, Runnable worker, Runnable about) {
        this.home = home;
        this.business = business;
        this.worker = worker;
        this.about = about;
        VBox content = new VBox(contactHero(), actions(), footer());
        content.setAlignment(Pos.TOP_CENTER);
        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color:transparent;-fx-border-width:0;");
        BorderPane page = new BorderPane(scroll);
        page.setTop(header());
        page.setStyle("-fx-background-color:#fff8f0;");
        return new Scene(page, 1400, 780);
    }

    private BorderPane header() {
        ImageView logo = image("/assets/logo/dihadi logo.jpeg", 50, 50);
        logo.setViewport(new Rectangle2D(380, 0, 840, 840));
        logo.setPreserveRatio(true);
        HBox brand = new HBox(10, logo, label("DIHADI",
                "-fx-font-family:Georgia;-fx-font-size:25px;-fx-font-weight:800;-fx-text-fill:#735c00;"));
        brand.setAlignment(Pos.CENTER_LEFT);
        Button h = nav("Home", false), b = nav("Business", false), w = nav("Worker", false), a = nav("About Us", false);
        h.setOnAction(e -> run(home));
        b.setOnAction(e -> run(business));
        w.setOnAction(e -> run(worker));
        a.setOnAction(e -> run(about));
        HBox links = new HBox(20, h, b, w, nav("Recruiter", false), a, nav("Contact Us", true));
        links.setAlignment(Pos.CENTER);
        BorderPane bar = new BorderPane();
        bar.setLeft(brand);
        bar.setCenter(links);
        bar.setRight(new HBox(12, outline("Login"), primary("Sign Up")));
        bar.setPadding(new Insets(16, 42, 14, 42));
        bar.setStyle("-fx-background-color:#fff8f0;-fx-border-color:#d0c5af;-fx-border-width:0 0 1px 0;");
        return bar;
    }

    private StackPane contactHero() {
        StackPane hero = new StackPane(image("/assets/images/business/business1.png", 1400, 810));
        hero.setPrefHeight(810);
        HBox panel = new HBox(details(), form());
        panel.setPrefSize(1050, 454);
        panel.setPrefSize(1050, 500);
        panel.setMinHeight(350);
        panel.setMaxWidth(1050);

        panel.setStyle(
                "-fx-background-color:#efe1c9;-fx-background-radius:25px;-fx-effect:dropshadow(gaussian,rgba(31,27,19,.26),22,0,0,9px);");
        hero.getChildren().add(panel);
        StackPane.setAlignment(panel, Pos.CENTER);
        return hero;
    }

    private VBox details() {
        VBox box = new VBox(26, label("Get in Touch", head(31)), label(
                "We are here to help bridge the gap between\ndigital infrastructure and human connection.\nReach out to us for any inquiries.",
                body(14)), line("⌕", "PHONE", "+91 9561789599"), line("✉", "EMAIL", "info@meridihadi.com"),
                line("⌖", "ADDRESS", "3rd Floor, Walhekar Properties,\nCore2web Technologies, Narhe, Pune"));
        box.setPrefWidth(398);
        box.setPadding(new Insets(52, 46, 40, 46));
        box.setStyle("-fx-background-color:#d8b52d;-fx-background-radius:25px 0 0 25px;");
        return box;
    }

    private VBox line(String icon, String title, String value) {
        Label i = label(icon, "-fx-font-size:21px;-fx-text-fill:#342f28;");
        VBox words = new VBox(5,
                label(title, "-fx-font-size:12px;-fx-font-weight:800;-fx-letter-spacing:1.5px;-fx-text-fill:#6a5520;"),
                label(value, body(14)));
        return new VBox(new HBox(16, i, words));
    }

    private VBox form() {
        GridPane grid = new GridPane();
        grid.setHgap(24);
        grid.setVgap(20);
        ColumnConstraints c = new ColumnConstraints();
        c.setPercentWidth(50);
        c.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(c, new ColumnConstraints());
        grid.getColumnConstraints().get(1).setPercentWidth(50);
        grid.add(field("Full Name", "John Doe"), 0, 0);
        grid.add(field("Email", "john@example.com"), 1, 0);
        grid.add(field("Mobile", "+91 00000 00000"), 0, 1);
        grid.add(field("Subject", "How can we help?"), 1, 1);
        TextArea message = new TextArea();
        message.setPromptText("Your message here...");
        message.setPrefRowCount(4);
        message.setPrefHeight(92);
        message.setMaxHeight(92);
        message.setWrapText(true);
        message.setStyle(input());
        VBox msg = new VBox(8, label("Message", caption()), message);
        Button send = new Button("SEND MESSAGE");
        send.setStyle(
                "-fx-background-color:#2c2921;-fx-background-radius:17px;-fx-text-fill:#fff8f0;-fx-font-size:12px;-fx-font-weight:800;-fx-padding:13px 28px;");
        VBox box = new VBox(22, label("Send us a message", head(18)), grid, msg, send);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPrefWidth(650);
        box.setPadding(new Insets(52, 48, 40, 48));
        box.setPadding(new Insets(42, 48, 32, 48));
        return box;
    }

    private VBox field(String name, String prompt) {
        TextField f = new TextField();
        f.setPromptText(prompt);
        f.setPrefHeight(40);
        f.setStyle(input());
        return new VBox(8, label(name, caption()), f);
    }

    private String caption() {
        return "-fx-font-size:12px;-fx-font-weight:700;-fx-letter-spacing:.8px;-fx-text-fill:#6a5520;";
    }

    private String input() {
        return "-fx-background-color:#e3e5e9;-fx-background-radius:0;-fx-border-color:transparent transparent #342f28 transparent;-fx-border-width:0 0 1px 0;-fx-font-family:Georgia;-fx-font-size:14px;-fx-prompt-text-fill:#687080;-fx-padding:10px 12px;";
    }

    private VBox actions() {
        HBox cards = new HBox(36,
                action("Scale Your\nOperations",
                        "Access our network of 300,000+ Verified Profiles to meet\nyour project demands efficiently.",
                        "BECOME A PARTNER", "#2f2c24", true),
                action("Discover Dignified\nOpportunities",
                        "Connect with 12,000+ Active Employers seeking reliable and\nskilled workforce.",
                        "POST A REQUIREMENT", "#d5b143", false));
        cards.setAlignment(Pos.CENTER);
        VBox section = new VBox(cards);
        section.setAlignment(Pos.CENTER);
        section.setPadding(new Insets(78, 42, 78, 42));
        section.setStyle("-fx-background-color:#fff8f0;");
        return section;
    }

    private VBox action(String title, String copy, String text, String bg, boolean outlined) {
        String colour = outlined ? "#fff8f0" : "#1f1b13";
        Button b = new Button(text);
        b.setStyle("-fx-background-color:" + (outlined ? "transparent" : "#2f2c24")
                + ";-fx-background-radius:18px;-fx-border-color:" + (outlined ? "#d5b143" : "transparent")
                + ";-fx-border-radius:18px;-fx-text-fill:#fff8f0;-fx-font-size:12px;-fx-font-weight:800;-fx-padding:12px 27px;");
        Region gap = new Region();
        VBox.setVgrow(gap, Priority.ALWAYS);
        VBox box = new VBox(18,
                label(title,"-fx-font-family:Georgia;-fx-font-size:32px;-fx-font-weight:700;-fx-text-fill:" + colour + ";"),
                label(copy, "-fx-font-family:Georgia;-fx-font-size:14px;-fx-text-fill:" + colour + ";"), gap, b);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPadding(new Insets(40, 52, 36, 52));
        box.setMinSize(510, 310);
        box.setPrefSize(510, 310);
        box.setMaxSize(510, 310);
        box.setStyle("-fx-background-color:" + bg
                + ";-fx-background-radius:22px;-fx-effect:dropshadow(gaussian,rgba(58,48,39,.10),18,0,0,6px);");
        return box;
    }

    private VBox footer() {
        Label brand = label("DIHADI",
                "-fx-font-family:Georgia;-fx-font-size:27px;-fx-font-weight:800;-fx-text-fill:#f1d679;");
        Region gap = new Region();
        HBox.setHgrow(gap, Priority.ALWAYS);
        HBox top = new HBox(20, brand, gap, label("© 2024 DIHADI. Mera Haq ~ Meri Dihadi. All Rights Reserved.",
                "-fx-font-size:12px;-fx-text-fill:#fff8f0;-fx-opacity:.82;"));
        VBox cols = new VBox(22,
                new HBox(92, column("Company", "About Us", "Careers", "Press"),
                        column("Opportunities", "For Workers", "For Businesses", "For Recruiters"),
                        column("Legal", "Terms of Service", "Privacy Policy", "Cookie Policy"),
                        column("Contact", "Contact Us", "Support", "Addresses")));
        Region line = new Region();
        line.setPrefHeight(1);
        line.setStyle("-fx-background-color:#978b7b;");
        VBox box = new VBox(34, top, line, cols);
        box.setPadding(new Insets(52, 86, 54, 86));
        box.setStyle("-fx-background-color:#6b5e52;");
        return box;
    }

    private VBox column(String heading, String... links) {
        VBox b = new VBox(15, label(heading,
                "-fx-font-family:Georgia;-fx-font-size:16px;-fx-font-weight:700;-fx-text-fill:#f1d679;"));
        for (String s : links)
            b.getChildren().add(label(s, "-fx-font-size:13px;-fx-text-fill:#fff8f0;-fx-opacity:.75;"));
        b.setPrefWidth(160);
        return b;
    }

    private ImageView image(String p, double w, double h) {
        ImageView v = new ImageView(load(p));
        v.setFitWidth(w);
        v.setFitHeight(h);
        v.setPreserveRatio(false);
        return v;
    }

    private Image load(String p) {
        var r = getClass().getResource(p);
        return r == null ? null : new Image(r.toExternalForm());
    }

    private Label label(String t, String s) {
        Label l = new Label(t);
        l.setStyle(s);
        return l;
    }

    private String body(int s) {
        return "-fx-font-family:Georgia;-fx-font-size:" + s + "px;-fx-text-fill:#4d4635;-fx-line-spacing:3px;";
    }

    private String head(int s) {
        return "-fx-font-family:Georgia;-fx-font-size:" + s + "px;-fx-font-weight:700;-fx-text-fill:#1f1b13;";
    }

    private Button nav(String t, boolean on) {
        Button b = new Button(t);
        b.setStyle("-fx-background-color:transparent;-fx-font-size:13px;-fx-font-weight:700;-fx-text-fill:"
                + (on ? "#735c00" : "#4d4635") + ";-fx-border-color:" + (on ? "#735c00" : "transparent")
                + ";-fx-border-width:0 0 2px 0;-fx-padding:8px 4px;");
        return b;
    }

    private Button primary(String t) {
        Button b = new Button(t);
        b.setStyle(
                "-fx-background-color:#d4af37;-fx-background-radius:18px;-fx-text-fill:#342f28;-fx-font-size:13px;-fx-font-weight:800;-fx-padding:11px 24px;");
        return b;
    }

    private Button outline(String t) {
        Button b = new Button(t);
        b.setStyle(
                "-fx-background-color:transparent;-fx-background-radius:18px;-fx-border-color:#806c47;-fx-border-radius:18px;-fx-text-fill:#342f28;-fx-font-size:13px;-fx-font-weight:700;-fx-padding:10px 23px;");
        return b;
    }

    private void run(Runnable r) {
        if (r != null)
            r.run();
    }
}
