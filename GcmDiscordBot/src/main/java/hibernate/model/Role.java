package hibernate.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description")
	private String roleDescription;

	@OneToMany(mappedBy = "role")
	private Set<MemberRoles> memberRoles = new HashSet<>();

	public Role() {
		super();
	}

	public Role(String roleName, String roleDescription, Set<MemberRoles> memberRoles) {
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.memberRoles = memberRoles;
	}

	@XmlElement(name = "ID", required = true)
	public int getId() {
		return id;
	}

	@XmlElement(name = "RoleName")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@XmlElement(name = "RoleDescription")
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@XmlTransient
	public Set<MemberRoles> getMemberRoles() {
		return memberRoles;
	}

	public void setMemberRoles(Set<MemberRoles> memberRoles) {
		this.memberRoles = memberRoles;
	}

	@Override
	public String toString() {
		return "\nRole id=" + id + "\nroleName=" + roleName + "\nroleDescription=" + roleDescription
				+ "\n----------------------------------" + "\n";
	}

}