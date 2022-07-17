package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "log")
@DynamicUpdate
public class Log implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "CONTEUDO", unique = false, nullable = false, columnDefinition="TEXT")
	private String conteudo;

	@Column(name = "VEZES", unique = false, nullable = false)
	private long vezes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Long getVezes() {
		return vezes;
	}

	public void setVezes(Long vezes) {
		this.vezes = vezes;
	}

}
