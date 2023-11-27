package com.flightreservation.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fightreservation.service.UserService;
import com.flightreservation.dao.UserRepository;
import com.flightreservation.entity.User;
import com.flightreservation.exceptions.FlightApplicationException;
import com.flightreservation.util.ErrorStatus;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	public User saveUser(User user) {
		try {
			if (user.getUsername().contains("[^`~|#{}=+-_]") || user.getPassword().contains("[^`~|#{}=+-_]"))
				throw new FlightApplicationException(ErrorStatus.USERNAME_PASSWORD_FORMAT_EXCEPTION.getCode(),
						ErrorStatus.USERNAME_PASSWORD_FORMAT_EXCEPTION.getDescription());
			userRepo.save(user);
			return user;
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());		}
	}

	@Override
	public String getUser(String username, String password) {
		try {
			User user = userRepo.findByusername(username);
			if (user == null)
				throw new FlightApplicationException(ErrorStatus.USER_NOT_FOUND.getCode(),
						ErrorStatus.USER_NOT_FOUND.getDescription());
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
				return "Logged in successfully";
			else
				return "Invalid creadentials";
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
	}

	@Override
	public User getUserDetails(String username) {
		User user = null;
		try {
			user = userRepo.findByusername(username);
			if (user == null)
				throw new FlightApplicationException(ErrorStatus.USER_NOT_FOUND.getCode(),
						ErrorStatus.USER_NOT_FOUND.getDescription());
		} catch (FlightApplicationException e) {
			throw new FlightApplicationException(e.getCode(), e.getDescription());
		} catch (Exception e) {
			throw new FlightApplicationException(ErrorStatus.GENERAL_ERROR.getCode(),
					ErrorStatus.GENERAL_ERROR.getDescription());
		}
		return user;
	}
}
