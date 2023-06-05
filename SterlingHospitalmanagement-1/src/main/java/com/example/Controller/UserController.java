package com.example.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.User;
import com.example.Exception.UserNotFoundException;
import com.example.Service.Impl.MyUserDetailsService;

@RestController
public class UserController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private MyUserDetailsService userDetailsService;

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		Map<String, String> errors = new HashMap<>();
		String field = null;
		String message = null;
		try {
			String pwd = user.getPassword();
			String bcryptpwd = passwordEncoder.encode(pwd);
			user.setPassword(bcryptpwd);

			User savedUser = userDetailsService.addUser(user);
			return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
		} catch (Exception ex) {

			field += 23000;

			message += "Duplication of unique field";
//
			// }
//				}
//			}
			// }
			errors.put(field, message);
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/users")
	public User updateUser(@RequestBody User user) throws UserNotFoundException {
		return userDetailsService.updateUser(user);
	}

}
