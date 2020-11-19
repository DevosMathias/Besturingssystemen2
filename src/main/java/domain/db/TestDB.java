package domain.db;

import domain.model.Test;

import java.util.List;

public interface TestDB {
    /**
     * Returns a list of all the positive covid-19 tests
     * @return an Arraylist of al the positive tests stored in the database
     * @throws DbException if something went wrong
     */
    List<Test> getAllTests();

    /**
     * Returns a list of all the positive covid-19 tests of the person with the given userid
     * @param userid the userid of the person we want all the positive tests from
     * @return an Arraylist of all the positive tests of the person stored in the database
     * @throws DbException if something went wrong
     */
    List<Test> getAllTestsPerson(String userid);

    /**
     * Returns the last positive covid-19 test of the person with the given userid
     * @param userid the userid of the person we want the last positive test from
     * @return the last positive test of the person stored in the database
     * @throws DbException if something went wrong
     */
    Test getLastTestPerson(String userid);

    /**
     * Stores the given test
     * @param test The test to be added
     * @throws DbException if something went wrong
     * @throws DbException if the test is null
     * @throws DbException if the test already exists
     */
    void addTest(Test test);
}
