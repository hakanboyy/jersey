package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENTDETI")
public class Department {
	@Id
	@Column(name = "DEPARTMENT_ID", length=0, unique=true, nullable=false)
	private int departmentId;
	@Column(name = "DEPARTMENT_NAME", length=30, unique=false, nullable=true)
	private String departmentName;
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getJsonString(){
		String json = null;
		json ="{\"DEPARTMENT_ID\":\"" + this.departmentId + "\","
				+"\"DEPARTMENT_NAME\":\"" + this.departmentName + "\"}";
		
		return json;
	}

}
