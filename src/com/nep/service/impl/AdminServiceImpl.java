package com.nep.service.impl;

import com.nep.service.AdminService;
import com.nep.util.DatabaseUtil;
import com.nep.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LogUtil.getLogger(AdminServiceImpl.class);

    @Override
    public boolean login(String logincode, String password) {
        String sql = "SELECT COUNT(*) FROM nepm WHERE account = ? AND password = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, logincode);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    boolean result = rs.getInt(1) > 0;
                    if (result) {
                        logger.info(String.format("管理员登录验证成功: account=%s", logincode));
                    } else {
                        logger.warning(String.format("管理员登录验证失败: account=%s", logincode));
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            logger.severe(String.format("管理员登录验证异常: account=%s, 错误=%s", logincode, e.getMessage()));
        }
        return false;
    }
}
