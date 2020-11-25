package domain.db;

import domain.model.Contact;
import util.DbConnectionService;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDB {
    private Connection connection;
    private String schema;

    public ContactDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }
    @Override
    public void add(Contact contact) {
        if (contact == null) {
            throw new DbException("Nothing to add.");
        }
        if (contactExists(contact)) {
            throw new DbException("Contact already exists");
        }
        String sql = String.format("INSERT INTO %s.contact (fname, lname, date, hour, gsm, email, userid) VALUES (?, ?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, contact.getFirstName());
            statementSQL.setString(2, contact.getLastName());
            statementSQL.setObject(3, contact.getDate());
            statementSQL.setObject(4, contact.getHour());
            statementSQL.setString(5, contact.getGsm());
            statementSQL.setString(6, contact.getEmail());
            statementSQL.setString(7, contact.getUserid());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private boolean contactExists(Contact contact) {
        List<Contact> contacts = getAllContactsPerson(contact.getUserid());
        for (Contact c : contacts) {
            if (c.getEmail().equals(contact.getEmail()) && c.getDate().equals(contact.getDate()) && c.getHour().equals(contact.getHour())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public List<Contact> getAllContactsPerson(String userid) {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ? ORDER BY date DESC, hour DESC, email", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();

            contacts = getResult(result);
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return contacts;
    }

    @Override
    public List<Contact> getAllContactsAdmin() {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact ORDER BY date DESC, hour DESC, email", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            contacts = getResult(result);
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return contacts;
    }

    @Override
    public List<Contact> getUnique(String userid) {
        List<Contact> contacts = new ArrayList<>();

        String sql = String.format("SELECT * FROM %s.contact AS C WHERE userid = ? AND C.date >= ALL (SELECT date FROM %s.contact WHERE email = C.email AND userid = C.userid) AND C.hour >= ALL (SELECT hour FROM %s.contact AS X WHERE X.email = C.email AND X.userid = C.userid AND X.date >= ALL (SELECT date FROM %s.contact WHERE email = X.email AND userid = X.userid)) ORDER BY date DESC, hour DESC", this.schema, this.schema, this.schema, this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();

            contacts = getResult(result);
        } catch (SQLException e) {
            throw new DbException(e);
        }

        return contacts;
    }

    @Override
    public List<Contact> getAllContactsPersonAfterDate(String userid, LocalDate date) {
        List<Contact> contacts = new ArrayList<>();

        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ? AND date >= ?", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            statementSQL.setObject(2, date);
            ResultSet result = statementSQL.executeQuery();

            contacts = getResult(result);

        } catch (SQLException e) {
            throw new DbException(e);
        }
        return contacts;
    }

    private List<Contact> getResult(ResultSet result) throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<>();
        while (result.next()) {
            String firstName = result.getString("fname");
            String lastName = result.getString("lname");
            LocalDate localDate = result.getObject("date", LocalDate.class);
            LocalTime hour = result.getObject("hour", LocalTime.class);
            String gsm = result.getString("gsm");
            String email = result.getString("email");
            String id = result.getString("userid");

            Contact contact = new Contact(firstName, lastName, localDate, hour, gsm, email, id);
            contacts.add(contact);
        }

        return contacts;
    }
}
