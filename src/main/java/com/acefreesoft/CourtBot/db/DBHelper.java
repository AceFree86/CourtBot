package com.acefreesoft.CourtBot.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class DBHelper {

    private final Connection conn = SQLiteConnection.connect();

    public void insertUser(DataModel model) {
        try {
            if (conn != null) {
                String sql = "INSERT INTO list_user(USER_ID,FIRST_NAME,ACTIVE) VALUES(?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, model.getUserId());
                ps.setString(2, model.getText());
                ps.setInt(3, model.getState());
                ps.executeUpdate();
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUserInput(DataModel model) {
        try {
            if (conn != null) {
                String sql = "INSERT INTO list_user_input(USER_ID,USER_INPUT,STATE) VALUES(?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, model.getUserId());
                ps.setString(2, model.getText());
                ps.setInt(3, model.getState());
                ps.executeUpdate();
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertResult(DataModel model) {
        try {
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO list_meetings(USER_ID,MEETINGS,STATE) VALUES(?,?,?)");
                ps.setInt(1, model.getUserId());
                ps.setString(2, model.getText());
                ps.setInt(3, model.getState());
                ps.executeUpdate();
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUser(int number) {
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "SELECT USER_ID FROM list_user WHERE USER_ID= '" + number + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return true;
                }
                rs.close();
                stmt.close();
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkRow(String tablet, String row, String text) {
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "SELECT " + row + " FROM " + tablet + " WHERE " + row + "= '" + text + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    return true;
                }
                rs.close();
                stmt.close();
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteOneRow(String TABLET, String ROW, String name) {
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM " + TABLET + " WHERE " + ROW + "= '" + name + "'";
                stmt.executeUpdate(sql);
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllData(String TABLET, String ROW, int number) {
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM " + TABLET + " WHERE " + ROW + "= '" + number + "'";
                stmt.executeUpdate(sql);
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<DataModel> getUserInput(int number) {
        ArrayList<DataModel> returnList = new ArrayList<>();
        try {
            if (conn != null) {
                String sql = "SELECT * FROM list_user_input WHERE USER_ID= '" + number + "'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    do {
                        int id = rs.getInt("ID");
                        int userId = rs.getInt("USER_ID");
                        String name = rs.getString("USER_INPUT");
                        int active = rs.getInt("STATE");
                        DataModel user = new DataModel(userId, name, active);
                        returnList.add(user);
                    } while (rs.next());
                }
                rs.close();
                stmt.close();
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return returnList;
    }

    public ArrayList<DataModel> getUserList() {
        ArrayList<DataModel> returnList = new ArrayList<>();
        try {
            if (conn != null) {
                String sql = "SELECT * FROM list_user";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    do {
                        int id = rs.getInt("ID");
                        int userId = rs.getInt("USER_ID");
                        String name = rs.getString("FIRST_NAME");
                        int active = rs.getInt("ACTIVE");
                        DataModel user = new DataModel(userId, name, active);
                        returnList.add(user);
                    } while (rs.next());
                }
                rs.close();
                stmt.close();
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return returnList;
    }
}
