package domain.db;

import domain.model.Test;
import util.DbConnectionService;

import javax.xml.transform.Result;
import javax.xml.validation.Schema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestDBSQL implements TestDB {
    private Connection connection;
    private String schema;

    public TestDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.test", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();

            while(result.next()) {
                String userid = result.getString("userid");
                LocalDate datum = result.getObject("date", LocalDate.class);

                Test test = new Test(userid, datum);
                tests.add(test);
            }
        } catch (SQLException e){
            throw new DbException(e);
        }

        return tests;
    }

    @Override
    public List<Test> getAllTestsPerson(String userid) {
        List<Test> tests = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.test WHERE userid = ?", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            ResultSet result = statementSQL.executeQuery();

            while(result.next()) {
                String id = result.getString("userid");
                LocalDate date = result.getObject("date", LocalDate.class);

                Test test = new Test(id, date);
                tests.add(test);
            }

        } catch (SQLException e) {
            throw new DbException(e);
        }

        return tests;
    }

    @Override
    public Test getLastTestPerson(String userid) {
        Test test = null;

        String sql = String.format("SELECT * FROM %s.test AS T WHERE userid = ? AND T.date >= ALL (SELECT date FROM %s.test WHERE userid = T.userid)", this.schema, this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            ResultSet result = statementSQL.executeQuery();

            while(result.next()) {
                String id = result.getString("userid");
                LocalDate date = result.getObject("date", LocalDate.class);
                test = new Test(id, date);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DbException(e);
        }

        return test;
    }

    @Override
    public void addTest(Test test) {
        if (test == null) {
            throw new DbException("Nothing to add");
        }
        if (testExists(test)) {
            throw new DbException("Test already exists");
        }

        String sql = String.format("INSERT INTO %s.test (userid, date) VALUES(?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, test.getUserid());
            statementSQL.setObject(2, test.getDate());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private boolean testExists(Test test) {
        List<Test> tests = getAllTestsPerson(test.getUserid());
        for (Test t : tests) {
            if (t.getUserid().equals(test.getUserid()) && t.getDate().equals(test.getDate())) {
                return true;
            }
        }
        return false;
    }
}
