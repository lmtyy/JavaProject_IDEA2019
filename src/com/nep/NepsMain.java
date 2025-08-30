package com.nep;

import com.nep.controller.NepsLoginViewController;
import com.nep.util.DatabaseUtil;
import com.nep.util.JavafxUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NepsMain extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        DatabaseUtil.getConnection();
        NepsLoginViewController.primaryStage = primaryStage;
        JavafxUtil.showStage(NepsMain.class,"view/NepsLoginView.fxml", primaryStage, "东软环保公众监督平台-公众监督员端");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
