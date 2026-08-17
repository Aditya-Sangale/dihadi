package com.dihadi.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** Premium paper-and-gold About page matching WorkerPage. */
public class AboutUs {
    private static final String CARD = "-fx-background-color:#fff8f0;-fx-background-radius:24px;-fx-border-color:#d0c5af;-fx-border-radius:24px;-fx-effect:dropshadow(gaussian,rgba(58,48,39,.10),18,0,0,6px);";

    public Scene getAboutScene(Runnable back) {
        return getAboutScene(back, null);
    }

    public Scene getAboutScene(Runnable back, Runnable workerAction) {
        VBox content = new VBox(26, hero(), mission(), stats(), faq(), footer());
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(26, 24, 30, 24));
        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color:transparent;-fx-border-width:0;");
        BorderPane page = new BorderPane(scroll);
        page.setTop(header(back, workerAction));
        StackPane root = new StackPane(page);
        root.setPadding(new Insets(24));
        setBackground(root);
        return new Scene(root, 1400, 780);
    }

    private VBox hero() {
        Label eyebrow = label("THE REALITY OF INDIA'S WORKFORCE",
                "-fx-font-size:12px;-fx-font-weight:800;-fx-text-fill:#735c00;-fx-letter-spacing:1.4px;");
        Label title = label("A day without work means a day without food.\nWe are here to change that.",
                "-fx-font-size:37px;-fx-font-weight:800;-fx-text-fill:#3a3027;-fx-line-spacing:4px;");
        Label copy = label(
                "DIHADI bridges the gap between the informal chowk and the digital future—eliminating middlemen, wage theft, and insecurity.",
                "-fx-font-size:16px;-fx-text-fill:#4d4635;-fx-line-spacing:3px;");
        copy.setWrapText(true);
        copy.setMaxWidth(525);
        VBox text = new VBox(15, eyebrow, title, copy);
        text.setAlignment(Pos.CENTER_LEFT);
        text.setPrefWidth(535);
        ImageView photo = image("/assets/images/generalLabour.jpeg", 535, 320);
        StackPane imageBox = new StackPane(photo);
        imageBox.setStyle(
                "-fx-background-color:#f5eddf;-fx-background-radius:24px;-fx-border-color:#d4af37;-fx-border-radius:24px;");
        HBox row = new HBox(34, text, imageBox);
        row.setAlignment(Pos.CENTER);
        row.setPadding(new Insets(28, 32, 28, 32));
        row.setMaxWidth(1180);
        row.setStyle(CARD);
        return new VBox(row);
    }

    private VBox mission() {
        VBox a = card("Our Mission",
                "To protect, empower, organize, and uplift hardworking laborers by providing transparent, digital access to dignified work and fair wages.",
                true);
        VBox b = card("Our Vision",
                "We are building more than an app: a global workforce marketplace where every laborer is recognized, protected, and able to build a stable life without fear of exploitation.",
                false);
        HBox row = new HBox(24, a, b);
        row.setAlignment(Pos.CENTER);
        VBox section = new VBox(row);
        section.setAlignment(Pos.CENTER);
        section.setPadding(new Insets(26));
        section.setMaxWidth(1180);
        section.setStyle("-fx-background-color:#efe7da;-fx-background-radius:24px;-fx-border-color:#d0c5af;-fx-border-radius:24px;");
        return section;
    }

    private VBox card(String title, String text, boolean quote) {
        Label h = label(title, "-fx-font-size:20px;-fx-font-weight:800;-fx-text-fill:#735c00;");
        Label p = label(text, "-fx-font-size:" + (quote ? "20px" : "16px") + ";-fx-font-style:"
                + (quote ? "italic" : "normal") + ";-fx-text-fill:#3a3027;-fx-line-spacing:4px;");
        p.setWrapText(true);
        VBox box = new VBox(12, h, p);
        box.setPadding(new Insets(28));
        box.setPrefWidth(535);
        box.setStyle(CARD);
        return box;
    }

    private VBox stats() {
        Label quote = label(
                "“Every worker deserves fair work, fair wages, and the dignity to build a better tomorrow.”",
                "-fx-font-size:20px;-fx-font-style:italic;-fx-text-fill:#f8f0e2;-fx-line-spacing:3px;");
        quote.setWrapText(true);
        quote.setMaxWidth(620);
        Label middlemen = label("100%", "-fx-font-size:28px;-fx-font-weight:800;-fx-text-fill:#e9c349;");
        Label middlemenText = label("MIDDLEMEN ELIMINATED",
                "-fx-font-size:11px;-fx-font-weight:700;-fx-text-fill:#f8f0e2;-fx-letter-spacing:1px;");
        Label zero = label("Zero", "-fx-font-size:28px;-fx-font-weight:800;-fx-text-fill:#e9c349;");
        Label zeroText = label("TOLERANCE FOR EXPLOITATION",
                "-fx-font-size:11px;-fx-font-weight:700;-fx-text-fill:#f8f0e2;-fx-letter-spacing:1px;");
        VBox a = new VBox(6, middlemen, middlemenText), b = new VBox(6, zero, zeroText);
        a.setAlignment(Pos.CENTER);
        b.setAlignment(Pos.CENTER);
        HBox row = new HBox(42, quote, a, b);
        row.setAlignment(Pos.CENTER);
        VBox out = new VBox(row);
        out.setPadding(new Insets(25, 32, 25, 32));
        out.setMaxWidth(1180);
        out.setStyle("-fx-background-color:#343027;-fx-background-radius:20px;");
        return out;
    }

    private VBox faq() {
        VBox list = new VBox(10);
        list.setMaxWidth(900);
        add(list, "What is DIHADI?",
                "DIHADI is a comprehensive digital labor workforce ecosystem designed to connect daily wage earners directly with contractors and enterprises, eliminating exploitative middlemen and ensuring fair, timely compensation.");
        add(list, "How does the platform protect workers?",
                "We provide verified digital labor IDs, transparent wage tracking directly to bank accounts, and an immutable record of work history, preventing wage theft and unauthorized deductions.");
        add(list, "Can contractors hire large teams?",
                "Yes. Contractors can source, manage, and pay large cohorts of verified workers through a dependable digital workflow.");
        add(list, "What about worker welfare?",
                "Welfare is built in, with access to financial services and safety-net options that help workers and their families plan for the future.");
        VBox out = new VBox(17, label("Understanding the DIHADI Ecosystem",
                "-fx-font-size:29px;-fx-font-weight:800;-fx-text-fill:#3a3027;"), list);
        out.setAlignment(Pos.CENTER);
        out.setPadding(new Insets(30, 24, 34, 24));
        out.setMaxWidth(1180);
        out.setStyle("-fx-background-color:#efe7da;-fx-background-radius:24px;-fx-border-color:#d0c5af;-fx-border-radius:24px;");
        return out;
    }

    private void add(VBox list, String qText, String answer) {
        Label q = label(qText, "-fx-font-size:16px;-fx-font-weight:800;-fx-text-fill:#3a3027;"),
                plus = label("+", "-fx-font-size:24px;-fx-text-fill:#735c00;-fx-font-weight:700;"),
                a = label(answer, "-fx-font-size:14px;-fx-text-fill:#4d4635;-fx-line-spacing:2px;");
        a.setWrapText(true);
        a.setVisible(false);
        a.setManaged(false);
        HBox head = new HBox(q, plus);
        head.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(q, Priority.ALWAYS);
        VBox item = new VBox(0, head, a);
        item.setPadding(new Insets(16, 20, 16, 20));
        item.setStyle(CARD);
        item.setCursor(javafx.scene.Cursor.HAND);
        item.setOnMouseClicked(e -> {
            boolean open = !a.isVisible();
            a.setVisible(open);
            a.setManaged(open);
            plus.setText(open ? "−" : "+");
            item.setSpacing(open ? 10 : 0);
        });
        list.getChildren().add(item);
    }

    private VBox footer() {
        ImageView logo = image("/assets/logo/dihadi logo.jpeg", 52, 52);
        logo.setPreserveRatio(true);
        VBox identity = new VBox(8,
                new HBox(10, logo, label("DIHADI", "-fx-font-size:24px;-fx-font-weight:800;-fx-text-fill:#e9c349;")),
                label("Mera Haq ~ Meri Dihadi", "-fx-font-size:15px;-fx-font-style:italic;-fx-text-fill:#f8f0e2;"));
        HBox main = new HBox(80, identity, footerCol("Explore", "Home", "Worker", "Business", "Contact Us"),
                footerCol("Reach us", "Pune, Maharashtra, India", "info@meridihadi.com", "9561789599"));
        VBox out = new VBox(20, main,
                label("© 2026 DIHADI · Fair work. Human dignity.", "-fx-font-size:12px;-fx-text-fill:#f8f0e2;-fx-opacity:.7;"));
        out.setPadding(new Insets(28, 42, 22, 42));
        out.setMaxWidth(1180);
        out.setStyle("-fx-background-color:#343027;-fx-background-radius:20px;-fx-border-color:#d0c5af;-fx-border-radius:20px;");
        return out;
    }

    private VBox footerCol(String title, String... items) {
        VBox b = new VBox(7);
        b.getChildren().add(label(title, "-fx-font-size:13px;-fx-font-weight:800;-fx-text-fill:#e9c349;"));
        for (String item : items)
            b.getChildren().add(label(item, "-fx-font-size:13px;-fx-text-fill:#f8f0e2;-fx-opacity:.82;"));
        return b;
    }

    private BorderPane header(Runnable back, Runnable workerAction) {
        ImageView logo = image("/assets/logo/dihadi logo.jpeg", 54, 54);
        logo.setPreserveRatio(true);
        HBox brand = new HBox(10, logo,
                label("DIHADI", "-fx-font-size:25px;-fx-font-weight:800;-fx-text-fill:#735c00;"));
        brand.setAlignment(Pos.CENTER_LEFT);
        HBox nav = new HBox(12);
        nav.setAlignment(Pos.CENTER);
        for (String n : new String[] { "Home", "Business", "Worker", "Recruiter", "About Us", "Contact Us" }) {
            Button b = navButton(n, n.equals("About Us"));
            b.setOnAction(e -> AppNavigator.open((Stage) b.getScene().getWindow(), n));
            nav.getChildren().add(b);
        }
        Button login = outline("Login"), signup = primary("Sign Up");
        login.setOnAction(e -> AppNavigator.login());
        signup.setOnAction(e -> AppNavigator.signUp((Stage) signup.getScene().getWindow(), () -> AppNavigator.open((Stage) signup.getScene().getWindow(), "About Us")));
        login.setMouseTransparent(true); signup.setMouseTransparent(true);
        BorderPane bar = new BorderPane();
        bar.setLeft(brand);
        bar.setCenter(nav);
        bar.setRight(new HBox(10, login, signup));
        bar.setPadding(new Insets(16, 24, 14, 24));
        bar.setStyle("-fx-background-color:#f3e7ce;-fx-border-color:#d0c5af;-fx-border-width:0 0 1px 0;");
        return bar;
    }

    private Button navButton(String t, boolean active) {
        Button b = new Button(t);
        b.setStyle("-fx-background-color:transparent;-fx-font-size:13px;-fx-font-weight:700;-fx-text-fill:"
                + (active ? "#735c00" : "#4d4635") + ";-fx-border-color:" + (active ? "#735c00" : "transparent")
                + ";-fx-border-width:0 0 2px 0;-fx-padding:8px 4px;");
        return b;
    }

    private Button primary(String t) {
        Button b = new Button(t);
        b.setStyle(
                "-fx-background-color:#d4af37;-fx-background-radius:999px;-fx-text-fill:#3a3027;-fx-font-weight:700;-fx-padding:10px 20px;");
        return b;
    }

    private Button outline(String t) {
        Button b = new Button(t);
        b.setStyle(
                "-fx-background-color:#fbf3e5;-fx-background-radius:999px;-fx-border-color:#735c00;-fx-border-radius:999px;-fx-text-fill:#735c00;-fx-font-weight:700;-fx-padding:9px 18px;");
        return b;
    }

    private Label label(String t, String s) {
        Label l = new Label(t);
        l.setStyle("-fx-font-family:'Segoe UI',sans-serif;" + s);
        return l;
    }

    private ImageView image(String p, double w, double h) {
        ImageView v = new ImageView(load(p));
        if (p.contains("/assets/logo/")) {
            v.setViewport(new Rectangle2D(380, 0, 840, 840));
            v.setPreserveRatio(true);
        }
        v.setFitWidth(w);
        v.setFitHeight(h);
        v.setSmooth(true);
        return v;
    }

    private Image load(String p) {
        var r = getClass().getResource(p);
        return r == null ? null : new Image(r.toExternalForm());
    }

    private void setBackground(StackPane root) {
        var r = getClass().getResource("/assets/images/background image.jpeg");
        if (r == null) {
            root.setBackground(
                    new Background(new BackgroundFill(Color.web("#f3e7ce"), CornerRadii.EMPTY, Insets.EMPTY)));
            return;
        }
        root.setBackground(new Background(new BackgroundFill(Color.web("#f3e7ce99"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void start(Stage stage) {
        stage.setScene(getAboutScene(null));
        stage.setTitle("Dihadi - About Us");
        stage.setMaximized(true);
        stage.show();
    }
}
