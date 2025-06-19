package com.nep.service.impl;

import com.nep.controller.NepsFeedbackViewController;
import com.nep.controller.NepsSelectAqiViewController;
import com.nep.entity.Supervisor;
import com.nep.service.SupervisorService;
import com.nep.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupervisorServiceImpl implements SupervisorService {

    @Override
    public boolean login(String loginCode, String password) {
        String sql = "SELECT * FROM neps WHERE account = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, loginCode);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Supervisor s = new Supervisor();
                s.setLoginCode(rs.getString("account"));
                s.setPassword(rs.getString("password"));
                s.setRealName(rs.getString("name"));
                s.setSex(rs.getString("gender"));

                // 当前登录用户共享到下一个界面
                NepsSelectAqiViewController.supervisor = s;
                NepsFeedbackViewController.supervisor = s;

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // 已注册
                }
            }

            // 插入新用户
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, supervisor.getLoginCode());
                insertStmt.setString(2, supervisor.getPassword());
                insertStmt.setString(3, supervisor.getRealName());
                insertStmt.setString(4, supervisor.getSex());
                insertStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
