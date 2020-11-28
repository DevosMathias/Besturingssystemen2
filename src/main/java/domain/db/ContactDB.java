package domain.db;

import domain.model.Contact;

import java.time.LocalDate;
import java.util.List;

public interface ContactDB {

    /**
     * Stores the given contact
     * @param contact The contact to be added
     * @throws DbException if something went wrong
     */
    
    void add(Contact contact);

    /**
     * Deletes the given contact
     * @param contact The contact to be deleted
     * @throws DbException if something went wrong
     */
    void delete(Contact contact);

    /**
     * Returns a list of all the contacts stored in the database for a person
     * @param userid the userid of the person we want the contacts from
     * @return an Arraylist of all the contacts stored in the database
     * @throws DbException if something went wrong
     */
    List<Contact> getAllContactsPerson(String userid);

    /**
     * Returns a list of al the unique contacts stored in the database
     * @param userid the userid of the person we want the unique contacts from
     * @return an Arraylist of all t he unique contacts stored in the database
     * @throws DbException if something went wrong
     */
    List<Contact> getUnique(String userid);

    /**
     * Returns a list of all the contacts stored in the database
     * @return an Arraylist of all the contacts stored in the database
     * @throws DbException if something went wrong
     */
    List<Contact> getAllContactsAdmin();

    /**
     * Returns a list of all the contacts of a person after a positive covid-19 test
     * @param userid the userid of the person we want the contacts from
     * @param date the date the positive covid-19 test found place
     * @return an Arraylist of all the contacts stored in the database
     * @throws DbException if something went wrong
     */
    List<Contact> getAllContactsPersonAfterDate(String userid, LocalDate date);
}