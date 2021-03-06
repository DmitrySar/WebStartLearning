package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLInputElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

public class Controller {

    WebEngine engine;

    @FXML
    private Slider fontSize;

    @FXML
    private TextArea editor;

    @FXML
    private WebView view;

    @FXML
    private void setFontSize() {
        editor.setFont(Font.font(fontSize.getValue()));
    }

    @FXML
    private void editorToView() {
        engine.loadContent(editor.getText());
    }

    @FXML
    private void testId() {
        executeScript();
        Document document = engine.getDocument();
        HTMLInputElement element = (HTMLInputElement) document.getElementById("c");
        document.getElementById("injection").setTextContent(element.getValue());
//       injection information to web page from Java uses Thread
        Thread injectionTime = new Thread(() -> {
            while (!interrupted()) {
                Platform.runLater(() -> document.getElementById("sel").setTextContent(new Date().toString()));
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        injectionTime.setDaemon(true);
        injectionTime.start();

    }

    @FXML
    private void executeScript() {
        ((JSObject) engine.executeScript("window")).call("fill", "sel");
    }

    @FXML
    private void initialize() throws IOException {
        Files.lines(Paths.get("Java/res/index.html")).forEach(s -> editor.appendText(s + "\n"));
        engine = view.getEngine();
        engine.loadContent(editor.getText());
        Thread injectObjectToHTML = new Thread(() -> {
            while (!interrupted()) {

                try {
                    Platform.runLater(() -> {
                    JSObject injectionObject = (JSObject) engine.executeScript("window");
                    injectionObject.setMember("app", new Counter());
                });
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        injectObjectToHTML.setDaemon(true);
        injectObjectToHTML.start();
    }
}
