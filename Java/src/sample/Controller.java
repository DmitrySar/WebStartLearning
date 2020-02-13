package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLInputElement;

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
        Document document = engine.getDocument();
//        String c = document.getElementById("a").getAttribute("value");
        HTMLInputElement element = (HTMLInputElement) document.getElementById("c");
        document.getElementById("sel").setTextContent(element.getValue());
//       element.select();
//        Thread injectionTime = new Thread(new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                while (!interrupted()) {
//                    Platform.runLater(() -> document.getElementById("sel").setTextContent(new Date().toString()));
//                    sleep(1000);
//                }
//                return null;
//            }
//        });
//        injectionTime.setDaemon(true);
//        injectionTime.start();

    }

    @FXML
    private void initialize() {
        editor.setText("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title>Sample</title>\n" +
                "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"example.css\">\n" +
                "</head>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "function fill(s) {\n" +
                "\tdocument.getElementById('sel').style.background = \"red\";\n" +
                "}\n" +
                "\n" +
                "function sum(idA, idB, idC) {\n" +
                "\ta = document.getElementById(idA).value;\n" +
                "\tb = document.getElementById(idB).value;\n" +
                "\tlet c = Number(a) + Number(b);\n" +
                "\tdocument.getElementById(idC).value = c;\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "<body>\n" +
                "\t<p id = \"sel\">\n" +
                "                hhhhhhh\n" +
                "                </p>\n" +
                "\t<p>\n" +
                "\t<button type=\"button\" onclick=\"fill('ssss')\">click</button>\n" +
                "</p>\n" +
                "\t\t<p>\n" +
                "\t\t<input type=\"text\" id=\"a\" value=\"1\">\n" +
                "                +\n" +
                "                <input type=\"text\" id=\"b\" value=\"2\">\n" +
                "\t\t<button onclick=\"sum('a', 'b', 'c')\">=</button>\n" +
                "\t\t<input type=\"text\" id=\"c\">\n" +
                "\t</p>\n" +
                "</body>\n" +
                "</html>");
        engine = view.getEngine();
        engine.loadContent(editor.getText());
    }
}
