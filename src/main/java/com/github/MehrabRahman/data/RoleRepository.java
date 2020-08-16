package com.github.MehrabRahman.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository implements Dao<Role> {
    private boolean isCacheValid;
    private List<Role> cache;

    public RoleRepository() {
        resetCache();
    }

    public void resetCache() {
        isCacheValid = false;
        cache = null;
    }

    @Override
    public Role get(int id) {
        Role role = new Role();
        role.setId(id);
        String sql = "select * from authroles where id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            role.setAuthrole(rs.getString("authrole"));
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        if (isCacheValid) {
            return cache;
        }

        String sql = "select * from authroles";
        List<Role> roles = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Role temp = new Role();
                temp.setId(rs.getInt("id"));
                temp.setAuthrole(rs.getString("authrole"));
                roles.add(temp);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        cache = roles;
        isCacheValid = true;

        return roles;
    }

    @Override
    public void save(Role t) {
        // TODO Auto-generated method stub

    }

}