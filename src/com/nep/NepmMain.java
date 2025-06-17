package com.nep;

import com.nep.controller.NepmLoginViewController;
import com.nep.util.DatabaseUtil;
import com.nep.util.JavafxUtil;
import com.nep.util.LogUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class NepmMain extends Application {
    private static final Logger logger = LogUtil.getLogger(NepmMain.class);

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        DatabaseUtil.getConnection();
        NepmLoginViewController.primaryStage = primaryStage;
        JavafxUtil.showStage(NepmMain.class,"view/NepmLoginView.fxml", primaryStage, "东软环保公众监督平台-管理端");
        logger.info("管理端应用程序启动成功");
    }

    public static void main(String[] args) {
        logger.info("启动管理端应用程序");
        launch(args);
    }
}
