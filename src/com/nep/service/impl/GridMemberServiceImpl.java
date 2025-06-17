package com.nep.service.impl;

import com.nep.entity.GridMember;
import com.nep.service.GridMemberService;
import com.nep.util.DatabaseUtil;
import com.nep.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class GridMemberServiceImpl implements GridMemberService {
    private static final Logger logger = LogUtil.getLogger(GridMemberServiceImpl.class);

    @Override
    public GridMember login(String loginCode, String password) {
        String sql = "SELECT * FROM nepg WHERE account = ? AND password = ?";  // 要执行的sql语句

        try (Connection conn= DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {  // 执行sql语句的对象

            stmt.setString(1, loginCode);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {  // 处理数据
                if (rs.next()) {
                    GridMember gm = new GridMember();
                    gm.setLoginCode(rs.getString("account"));
                    gm.setRealName(rs.getString("name"));
                    gm.setPassword(rs.getString("password"));
                    gm.setGmTel(rs.getString("phoneNumber"));
                    gm.setState(rs.getString("state"));
                    logger.info(String.format("网格员登录验证成功: account=%s, name=%s", loginCode, gm.getRealName()));
                    return gm;
                } else {
                    logger.warning(String.format("网格员登录验证失败: account=%s", loginCode));
                }
            }
        } catch (SQLException e) {
            logger.severe(String.format("网格员登录验证异常: account=%s, 错误=%s", loginCode, e.getMessage()));
        }
        return null;
    }
 }
