package it.detecto.backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO  {

	private String id;
	private String firstName;
	private String lastName;

	public UserDTO() {
	}

}
