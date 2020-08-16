package com.github.MehrabRahman.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements Dao<User> {

    private Dao<Role> authroles;

    public UserRepository(Dao<Role> authroles) {
        this.authroles = authroles;
    }

    public User getUser(String name) {
        String sql = "select * from authusers where username = '" + name + "'";
        User user = new User();
        user.setUsername(name);
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                int authroleId = rs.getInt("authrole");
                user.setAuthrole(authroles.get(authroleId));
            }
            
        } catch (SQLException e) {
            //TODO: handle exception
        }
        return user;
    }

    @Override
    public User get(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(User t) {
        // TODO Auto-generated method stub

    }
}