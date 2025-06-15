package com.nep.controller;

import com.nep.NepmMain;
import com.nep.service.AdminService;
import com.nep.service.impl.AdminServiceImpl;
import com.nep.util.JavafxUtil;
import com.nep.util.LogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class NepmLoginViewController {
    private static final Logger logger = LogUtil.getLogger(NepmLoginViewController.class);

    @FXML
    private TextField txt_id;
    @FXML
    private PasswordField txt_password;
    //多态
    private AdminService adminService = new AdminServiceImpl();
    //主舞台
    public static Stage primaryStage;

    public TextField getTxt_id() {
        return txt_id;
    }

    public void setTxt_id(TextField txt_id) {
        this.txt_id = txt_id;
    }

    public PasswordField getTxt_password() {
        return txt_password;
    }

    public void setTxt_password(PasswordField txt_password) {
        this.txt_password = txt_password;
    }

    public void login() {
        try {
            boolean isLogin = adminService.login(txt_id.getText(), txt_password.getText());
            if (isLogin) {
                logger.info(String.format("管理员登录成功: ID=%s", txt_id.getText()));
                NepmMainViewController.primaryStage = primaryStage;
                JavafxUtil.showStage(NepmMain.class, "view/NepmMainView.fxml", primaryStage, "东软环保公众监督平台-管理端-主功能界面");
            } else {
                logger.warning(String.format("管理员登录失败: ID=%s", txt_id.getText()));
                JavafxUtil.showAlert(primaryStage, "登录失败", "用户名密码错误", "请重新输入用户名和密码", "warn");
            }
        } catch (Exception e) {
            logger.severe(String.format("登录异常: ID=%s, 错误=%s", txt_id.getText(), e.getMessage()));
        }
    }

}

