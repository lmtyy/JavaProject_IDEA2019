package com.nep.controller;

import com.nep.NepmMain;
import com.nep.util.JavafxUtil;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;

public class NepmMainViewController implements Initializable {

    //主舞台
    public static Stage primaryStage;
    @FXML
    private ImageView txt_imageView;
    @FXML
    private Label timeLabel;
    @FXML
    private Label weatherLabel;

    public ImageView getTxt_imageView() {
        return txt_imageView;
    }

    public void setTxt_imageView(ImageView txt_imageView) {
        this.txt_imageView = txt_imageView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化图片
        Image image = new Image("image/welcome");
        txt_imageView.setImage(image);
        txt_imageView.setPreserveRatio(false);   //禁用保持纵横比

        // 实时时钟
        Timeline clock = new Timeline(  // 创建 Timeline 对象
                // 第一个 KeyFrame: 在时间零点执行的操作
                new KeyFrame(Duration.ZERO, e -> timeLabel.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))),
                // 第二个 KeyFrame: 定义动画周期(每秒触发一次)
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        // 模拟天气数据
        String[] weathers = {" ☀晴天", " 🌧️小雨", " ⛅多云"};
        weatherLabel.setText(weathers[new Random().nextInt(weathers.length)]);
    }

    public void aqiInfo() {
        JavafxUtil.showSubStage(NepmMain.class, "view/NepmAqiInfoView.fxml", primaryStage, "东软环保公众监督平台-管理端-AQI反馈数据查询");
    }

    public void aqiAssign() {
        NepmAqiAssignViewController.aqiInfoStage = JavafxUtil.showSubStage(NepmMain.class, "view/NepmAqiAssignView.fxml", primaryStage, "东软环保公众监督平台-管理端-AQI反馈数据指派");;
    }

    public void aqiConfirm() {
        JavafxUtil.showSubStage(NepmMain.class, "view/NepmConfirmInfoView.fxml", primaryStage, "东软环保公众监督平台-管理端-AQI实测数据查询");
    }
}

