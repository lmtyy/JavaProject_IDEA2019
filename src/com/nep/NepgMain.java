package com.nep;
/*增加注释*/
import com.nep.controller.NepgLoginViewController;
import com.nep.util.DatabaseUtil;
import com.nep.util.JavafxUtil;
import com.nep.util.LogUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class NepgMain extends Application {
    private static final Logger logger = LogUtil.getLogger(NepgMain.class);

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        DatabaseUtil.getConnection();
        NepgLoginViewController.primaryStage = primaryStage;
        JavafxUtil.showStage(NepgMain.class,"view/NepgLoginView.fxml", primaryStage, "东软环保公众监督平台-网格员端");
        logger.info("网格员端应用程序启动成功");
    }

    public static void main(String[] args) {
        logger.info("启动网格员端应用程序");
        launch(args);
    }
}
