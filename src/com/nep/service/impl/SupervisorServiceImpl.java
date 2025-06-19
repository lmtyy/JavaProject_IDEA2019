package com.nep.service.impl;

import com.nep.controller.NepsFeedbackViewController;
import com.nep.controller.NepsSelectAqiViewController;
import com.nep.entity.Supervisor;
import com.nep.service.SupervisorService;
import com.nep.util.DatabaseUtil;
import com.nep.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SupervisorServiceImpl implements SupervisorService {
    private static final Logger logger = LogUtil.getLogger(SupervisorServiceImpl.class);

    @Override
    public boolean login(String loginCode, String password) {
        String sql = "SELECT * FROM neps WHERE account = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, loginCode);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Supervisor s = new Supervisor();
                    s.setLoginCode(rs.getString("account"));
                    s.setPassword(rs.getString("password"));
                    s.setRealName(rs.getString("name"));
                    s.setSex(rs.getString("gender"));

                    // 当前登录用户共享到下一个界面
                    NepsSelectAqiViewController.supervisor = s;
                    NepsFeedbackViewController.supervisor = s;

                    logger.info(String.format("监督员登录验证成功: account=%s, name=%s", loginCode, s.getRealName()));
                    return true;
                } else {
                    logger.warning(String.format("监督员登录验证失败: account=%s", loginCode));
                }
            }
        } catch (SQLException e) {
            logger.severe(String.format("监督员登录验证异常: account=%s, 错误=%s", loginCode, e.getMessage()));
        }
        return false;
    }

    @Override
    public boolean register(Supervisor supervisor) {
        String checkSql = "SELECT COUNT(*) FROM neps WHERE account = ?";
        String insertSql = "INSERT INTO neps (account, password, name, gender) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection()) {
            // 检查是否存在相同loginCode
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, supervisor.getLoginCode());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        logger.warning(String.format("监督员注册失败: 账号已存在 account=%s", supervisor.getLoginCode()));
                        return false; // 已注册
                    }
                }
            }

            // 插入新用户
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, supervisor.getLoginCode());
                insertStmt.setString(2, supervisor.getPassword());
                insertStmt.setString(3, supervisor.getRealName());
                insertStmt.setString(4, supervisor.getSex());
                int affectedRows = insertStmt.executeUpdate();
                if (affectedRows > 0) {
                    logger.info(String.format("监督员注册成功: account=%s, name=%s",
                            supervisor.getLoginCode(), supervisor.getRealName()));
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.severe(String.format("监督员注册异常: account=%s, 错误=%s",
                    supervisor.getLoginCode(), e.getMessage()));
        }
        return false;
    }
}