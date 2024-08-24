package com.makersharks.Entity;




import com.makersharks.security.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
	/**
	 * 
	 */
	@Id
	  @GeneratedValue
	  private Integer id;
	  private String name;
	  private String username;
	  private String password;

	  @Enumerated(EnumType.STRING)
	  private Role role;

	

}
