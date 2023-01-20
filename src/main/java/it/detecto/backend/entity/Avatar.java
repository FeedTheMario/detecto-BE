package it.detecto.backend.entity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class Avatar  {
	@Id
	private String id;
	private Integer age;

	public Avatar() {
	}
	public Avatar(Integer age) {
		this.age = age;
	}
}
