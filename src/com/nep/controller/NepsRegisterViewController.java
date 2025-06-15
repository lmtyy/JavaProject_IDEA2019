package com.nep.controller;

import com.nep.NepsMain;
import com.nep.entity.Supervisor;
import com.nep.service.SupervisorService;
import com.nep.service.impl.SupervisorServiceImpl;
import com.nep.util.JavafxUtil;
import com.nep.util.LogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class NepsRegisterViewController {
    private static final Logger logger = LogUtil.getLogger(NepsRegisterViewController.class);

    @FXML
    private TextField txt_id;
    @FXML
    private PasswordField txt_password;
    @FXML
    private PasswordField txt_repassword;
    @FXML
    private TextField txt_realName;
    @FXML
    private RadioButton txt_sex_male;
    @FXML
    private RadioButton txt_sex_female;
    //主舞台
    public static Stage primaryStage;
    //多态
    private SupervisorService supervisorService = new SupervisorServiceImpl();
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
    public PasswordField getTxt_repassword() {
        return txt_repassword;
    }
    public void setTxt_repassword(PasswordField txt_repassword) {
        this.txt_repassword = txt_repassword;
    }
    public TextField getTxt_realName() {
        return txt_realName;
    }
    public void setTxt_realName(TextField txt_realName) {
        this.txt_realName = txt_realName;
    }

    public void register(){
        try {
            if(!txt_password.getText().equals(txt_repassword.getText())){
                logger.warning("密码不一致: " + txt_id.getText());
                JavafxUtil.showAlert(primaryStage, "注册失败", "两次输入密码不一致", "请重新输入确认密码","warn");
                txt_repassword.setText("");
                return;
            }

            Supervisor supervisor = new Supervisor();
            supervisor.setLoginCode(txt_id.getText());
            supervisor.setPassword(txt_password.getText());
            supervisor.setRealName(txt_realName.getText());

            // Get the selected sex
            String sex = txt_sex_male.isSelected() ? txt_sex_male.getText() : txt_sex_female.getText();
            supervisor.setSex(sex);

            boolean flag = supervisorService.register(supervisor);
            if(flag){
                logger.info(String.format("用户注册成功: 手机号=%s", txt_id.getText()));
                JavafxUtil.showAlert(primaryStage, "注册成功", txt_id.getText()+" 账号注册成功!","可以进行用户登录!" ,"info");
            }else{
                logger.warning(String.format("手机号已存在: %s", txt_id.getText()));
                JavafxUtil.showAlert(primaryStage, "注册失败", "手机号已被注册", "请重新输入注册手机号码","warn");
                txt_id.setText("");
                return;
            }
            //跳转到登录界面进行登录
            JavafxUtil.showStage(NepsMain.class,"view/NepsLoginView.fxml", primaryStage,"东软环保公众监督平台-公众监督员端");
        } catch (Exception e) {
            logger.severe(String.format("注册异常: 手机号=%s, 错误=%s", txt_id.getText(), e.getMessage()));
        }

    }

    public void back(){
        JavafxUtil.showStage(NepsMain.class,"view/NepsLoginView.fxml", primaryStage,"东软环保公众监督平台-公众监督员端");
    }
}

