package com.BitespeedApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

interface ContactRepository {
	Contact save(Contact contact);

	List<Contact> findByEmailAndPhoneNumber(String email, String phoneNumber);

	List<Contact> findByEmail(String email);

	List<Contact> findByPhoneNumber(String phoneNumber);
}

@Service
public class ContactService {

	private static final String JDBC_URL = "jdbc:h2:mem:testdb";
	private static final String JDBC_USER = "sa";
	private static final String JDBC_PASSWORD = "";

	@Autowired
	private ContactRepository contactRepository;

	public Contact identifyContact(String email, String phoneNumber) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			List<Contact> existingContacts = contactRepository.findByEmailAndPhoneNumber(email, phoneNumber);
			if (!existingContacts.isEmpty()) {
				return existingContacts.get(0);
			}

			existingContacts = contactRepository.findByEmail(email);
			if (!existingContacts.isEmpty()) {
				return createSecondaryContact(existingContacts.get(0), email, phoneNumber, conn);
			}

			existingContacts = contactRepository.findByPhoneNumber(phoneNumber);
			if (!existingContacts.isEmpty()) {
				return createSecondaryContact(existingContacts.get(0), email, phoneNumber, conn);
			}

			return createPrimaryContact(email, phoneNumber, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Contact createPrimaryContact(String email, String phoneNumber, Connection conn) throws SQLException {
		String sql = "INSERT INTO contact (email, phoneNumber, linkPrecedence) VALUES (?, ?, 'PRIMARY')";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, email);
			ps.setString(2, phoneNumber);
			ps.executeUpdate();
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					long id = generatedKeys.getLong(1);
					return new Contact(id, email, phoneNumber, null, sql, null, null, null);
				}
			}
		}
		return null;
	}

	private Contact createSecondaryContact(Contact primaryContact, String email, String phoneNumber, Connection conn)
			throws SQLException {
		String sql = "INSERT INTO contact (email, phoneNumber, linked_id, linkPrecedence) VALUES (?, ?, ?, 'SECONDARY')";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, email);
			ps.setString(2, phoneNumber);
			ps.setLong(3, primaryContact.getId());
			ps.executeUpdate();
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					long id = generatedKeys.getLong(1);
					return new Contact(id, email, phoneNumber, primaryContact, sql, null, null, null);
				}
			}
		}
		return null;
	}
}