package hibernate.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="formulaire")
public class Formulaire implements Serializable {	
	private static final long serialVersionUID = -32050276291138241L;
	
	private Long id = null;
	private String nom = null;
	private List<Question> questions = null;
	
	public Formulaire(){}
	
	public Formulaire(Long id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public Formulaire(String nom) {
		this.nom = nom;
	}
	
	@Id 
	@SequenceGenerator(name="formulaire_id_seq", sequenceName="formulaire_id_seq", allocationSize=1)
	@GeneratedValue(generator = "formulaire_id_seq", strategy = GenerationType.SEQUENCE)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="nom")
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@OneToMany(mappedBy="formulaire")
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Transient
	@Override
	public String toString() {
		return "Formulaire:{ id:"+id+"\', nom: \'"+nom+"\'}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formulaire other = (Formulaire) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
