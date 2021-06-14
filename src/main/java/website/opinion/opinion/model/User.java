package website.opinion.opinion.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="user")
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(List<Opinion> opinions) {
		this.opinions = opinions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column
    private String password;

    @Column
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Opinion> opinions = new ArrayList<>();

    public User(String username, String email, String password, String role) {
        super();
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
    }

	public User() {
		// TODO Auto-generated constructor stub
	}
}
