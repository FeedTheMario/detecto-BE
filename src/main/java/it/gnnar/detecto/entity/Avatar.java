package it.gnnar.detecto.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SelectBeforeUpdate
@DynamicUpdate
@Setter
@Getter
public class Avatar  {
	@Id
	@GeneratedValue
	private Long id;
	private Integer age;

	public Avatar() {
	}
	public Avatar(Integer age) {
		this.age = age;
	}
}
