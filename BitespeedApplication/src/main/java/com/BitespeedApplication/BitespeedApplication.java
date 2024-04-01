package com.BitespeedApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BitespeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitespeedApplication.class, args);
	}

}

@RestController
class ContactController {

	@Autowired
	private ContactService contactService;

	@PostMapping("/identify")
	public ResponseEntity<ContactResponse> identifyContact(@RequestBody IdentifyRequest request) {
		Contact contact = contactService.identifyContact(request.getEmail(), request.getPhoneNumber());
		ContactResponse response = new ContactResponse(); // You need to implement this class
		// Populate response according to the format
		return ResponseEntity.ok(response);
	}
}