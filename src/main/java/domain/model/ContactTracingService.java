package domain.model;

import domain.db.*;
import domain.model.Contact;
import domain.model.Person;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactTracingService {
	private PersonDB personDB = new PersonDBSQL();
	private ContactDB contactDB = new ContactDBSQL();
	private TestDB testDB = new TestDBSQL();

	
	public ContactTracingService() {

	}

	//Methods for personDB
	public Person getPerson(String personId){
		return personDB.get(personId);
	}
	
	public List<Person> getAllPersons(){
		return personDB.getAll();
	}

	public void addPerson(Person person){
		personDB.add(person);
	}
	
	/*public void updatePerson(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}*/
	
	/*public void deletePerson(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}

	public int getNumberOfPersons() {
		return persons.size();
	}*/

	public void changeMailPerson(String userid, String newMail) {
		personDB.changeMail(userid, newMail);
	}

	public List<Person> getAllPersonsWithoutTest() {
		return personDB.getAllPersonsWithoutTest();
	}

	//methods for contact
	public void addContact(Contact contact) {
		contactDB.add(contact);
	}

	public List<Contact> getAllContactsPerson(String userid) {
		return contactDB.getAllContactsPerson(userid);
	}

	public List<Contact> getUniqueContact(String userid) {
		return contactDB.getUnique(userid);
	}

	public List<Contact> getAllContactsAdmin() {
		return contactDB.getAllContactsAdmin();
	}

	public List<Contact> getAllContactsPersonAfterDate(String userid, LocalDate date) {
		return contactDB.getAllContactsPersonAfterDate(userid, date);
	}

	//methods for test
	public List<Test> getAllTestsPerson(String userid) {
		return testDB.getAllTestsPerson(userid);
	}

	public Test getTestLastPerson(String userid) {
		return testDB.getLastTestPerson(userid);
	}

	public List<Test> getAllTests() {
		return testDB.getAllTests();
	}

	public void addTest(Test test) {
		testDB.addTest(test);
	}
}
