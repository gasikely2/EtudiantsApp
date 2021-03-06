package mg.laiso.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import mg.laiso.MainApp;

/**
 * Created by Laiso on 06/10/2016.
 */
public class ParametresController {

    @FXML
    private TextField fieldAdresse;

    @FXML
    private TextField fieldPort;

    @FXML
    private Button btnValider;

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnTesterConnexion;

    @FXML
    private Label labelErreur;

    private Stage parametreStage;
    private MainApp mainApp;

    @FXML
    public void initialize(){

    }

    public void setParametreStage(Stage parametreStage) {
        this.parametreStage = parametreStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private boolean testerConnexion() {
        labelErreur.setText("");
        if (isValid()) {
            if (mainApp.testerConnection(fieldAdresse.getText().trim(), Integer.parseInt(fieldPort.getText().trim()))) {
                labelErreur.setText("Connexion réussie"); labelErreur.setTextFill(Paint.valueOf("GREEN"));return true;
            }
            else {
                labelErreur.setText("Impossible d'établir la connexion");
                labelErreur.setTextFill(Paint.valueOf("RED"));
            }
        }
        return false;
    }

    @FXML
    private void valider() {
        labelErreur.setText("");
        if (isValid()) {
            if(testerConnexion()) {
                mainApp.setAdresseIP(fieldAdresse.getText().trim());
                mainApp.setPort(Integer.parseInt(fieldPort.getText().trim()));
                parametreStage.close();
            }
        }
    }

    @FXML
    private void annuler() {
        parametreStage.close();
    }

    private boolean isValid() {
        String message = "";

        if (fieldAdresse.getText() == null || fieldAdresse.getText().length() == 0) {
            message += "Adresse du serveur non valide";
        }

        if (fieldPort.getText() == null || fieldPort.getText().length() == 0) {
            message += (message.length() > 0 ? " - " : "") + "Numero de port non valide";
        }

        labelErreur.setText(message);
        labelErreur.setTextFill(Paint.valueOf("RED"));

        return message.length() == 0;
    }
}
