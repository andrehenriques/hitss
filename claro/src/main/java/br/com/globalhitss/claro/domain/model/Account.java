package br.com.globalhitss.claro.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.globalhitss.claro.helper.BCryptyHelper;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="accounts")
public class Account implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", nullable=false)
	@Getter @Setter
	private Date createdAt;
	
	public Account() {

	}
	
	public Account(Long id) {
		this.id = id;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@PrePersist
	public void prePersist() {
		setPassword(BCryptyHelper.encode(getPassword()));
		setCreatedAt(Date.from(Instant.now()));
	}


}
