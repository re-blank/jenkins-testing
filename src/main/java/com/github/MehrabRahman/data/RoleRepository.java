package com.github.MehrabRahman.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepository implements Dao<Role> {

    @Override
    public Role get(int id) {
        Role role = null;
        String sql = "select * from authroles where id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            role = new Role(rs.getInt("id"), rs.getString("authrole"));
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return role;
    }

    @Override
    public void save(Role t) {
        // TODO Auto-generated method stub

    }

}