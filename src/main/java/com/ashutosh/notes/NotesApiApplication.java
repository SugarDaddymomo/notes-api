package com.ashutosh.notes;

import com.ashutosh.notes.model.Role;
import com.ashutosh.notes.model.User;
import com.ashutosh.notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class NotesApiApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(NotesApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setNumber("9988776655");
		user.setPassword(passwordEncoder.encode("P@ssword98"));
		user.setRole(Role.ADMIN);
		try {
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println("User already added");
		}

	}
}
