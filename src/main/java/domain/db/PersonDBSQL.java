package domain.db;

import domain.model.Role;
import util.DbConnectionService;
import domain.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private Connection connection;
    private String schema;

    public PersonDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add.");
        }
        if (personExists(person)) {
            throw new DbException("User already exists");
        }
        String sql = String.format("INSERT INTO %s.person (userid, email, password, fname, lname, role) VALUES (?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getEmail());
            statementSQL.setString(3, person.getPassword());
            statementSQL.setString(4, person.getFirstName());
            statementSQL.setString(5, person.getLastName());
            statementSQL.setString(6, person.getRole().getValue());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private boolean personExists(Person person) {
        List<Person> persons = getAll();
        for (Person p : persons) {
            if (p.getUserid().equals(person.getUserid())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person ORDER BY fname, lname", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String fname = result.getString("fname");
                String lname = result.getString("lname");
                String role = result.getString("role");


                Person person = new Person(userid, email, password, fname, lname, Role.valueOf(role.toUpperCase()));
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return persons;
    }

    @Override
    public Person get(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new DbException("No id given");
        }
        Person person = null;
        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, id.toLowerCase());
            ResultSet result = statementSQL.executeQuery();

            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String fname = result.getString("fname");
                String lname = result.getString("lname");
                String role = result.getString("role");

                person = new Person(userid, email, password, fname, lname, Role.valueOf(role.toUpperCase()));
            }

        } catch (SQLException e) {
            throw new DbException(e);
        }

        if (person == null) {
            throw new DbException("User with id: " + id + " does not exist");
        }
        return person;
    }

    public void changeMail(String userid, String newMail) {
        String sql = String.format("UPDATE %s.person SET email = ? WHERE userid = ?", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, newMail);
            statementSQL.setString(2, userid);
            statementSQL.execute();

        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Person> getAllPersonsWithoutTest() {
        List<Person> persons = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person AS S LEFT OUTER JOIN %s.test AS T USING(userid) WHERE T.userid IS NULL", this.schema, this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();

            while(result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String fname = result.getString("fname");
                String lname = result.getString("lname");
                String role = result.getString("role");

                Person person = new Person(userid, email, password, fname, lname, Role.valueOf(role.toUpperCase()));
                persons.add(person);
            }
        } catch (SQLException e){
            throw new DbException(e);
        }

        return persons;
    }
}
