package controller;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;

import model.Department;

@Path("/department")
public class DepartmentService {
	@POST
	@Path("/register")
	public String register(@FormParam("DEPARTMENT_ID") int departmentId,
			@FormParam("DEPARTMENT_NAME") String departmentName) {
		try {
			Department model = new Department();
			model.setDepartmentId(departmentId);
			model.setDepartmentName(departmentName);

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();

			session.save(model);

			session.getTransaction().commit();
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(@FormParam("DEPARTMENT_ID") int departmentId, @FormParam("DEPARTMENT_NAME") String departmentName) {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();

			session.beginTransaction();
			Department model = session.get(Department.class, departmentId);
			session.getTransaction().commit();

			if (!departmentName.equals("")) {
				model.setDepartmentName(departmentName);
			}
			session.beginTransaction();
			session.save(model);
			session.getTransaction().commit();
			
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public String delUser(@FormParam("DEPARTMENT_ID") int departmentId) {

		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();

			Department model = session.get(Department.class, departmentId);
			session.delete(model);

			session.getTransaction().commit();
			session.close();

			return "{\"return\":\"success\"}";

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}

	
	@POST
	@Path("/pull")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsers() {
		try {

			Session session = new Configuration().configure().buildSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings({ "unchecked", "deprecation" })
			List<Department> modelList = session.createCriteria(Department.class).list();

			session.getTransaction().commit();
			session.close();

			// json array formatýna çevriliyor.
			String response = "[";
			for (int x = 0; x < modelList.size(); x++) {
				response += modelList.get(x).getJsonString();
				if (x != modelList.size() - 1) {
					response += ",";
				}
			}
			response += "]";

			return response;

		} catch (Exception e) {
			// TODO: handle exception
			return "{\"return\":\"" + e.getMessage() + "\"}";
		}
	}
	
	
	

}
