package com.github.MehrabRahman.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements Dao<User> {
    public boolean getUser(String name) {
        String sql = "select * from authusers where username = '" + name + "'";
        boolean result = false;
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            result = statement.execute(sql);
            
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public User get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(User t) {
        // TODO Auto-generated method stub

    }
}