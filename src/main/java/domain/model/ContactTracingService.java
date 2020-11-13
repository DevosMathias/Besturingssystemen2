package domain.model;

import domain.db.*;
import domain.model.Contact;
import domain.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactTracingService {
	private PersonDB personDB = new PersonDBSQL();
	private ContactDB contactDB = new ContactDBSQL();

	
	public ContactTracingService() {
		/*Person administrator = new Person();
		administrator.setUserid("admin");
		administrator.setEmail("admin@ucll.be");
		administrator.setPasswordHashed("t");
		administrator.setFirstName("Ad");
		administrator.setLastName("Ministrator");
		administrator.setRole(Role.ADMINISTRATOR);
		addPerson(administrator);*/
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

	//methods for contact
	public void addContact(Contact contact) {
		contactDB.add(contact);
	}

	public List<Contact> getAllContacts() {
		return contactDB.getAll();
	}

	public List<Contact> getUniqueContact() {return contactDB.getUnique();}
}
