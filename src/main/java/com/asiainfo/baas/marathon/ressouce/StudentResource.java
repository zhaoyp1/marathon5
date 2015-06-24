package com.asiainfo.baas.marathon.ressouce;

import java.util.List;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.asiainfo.baas.marathon.entity.Student;


@Singleton
@Path("/student")
public class StudentResource {
	
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Student postStudent(Student student, @Context HttpServletRequest request) {
		student.addStudent();
		return student; 
	}
	
//	@DELETE
//	@Path("/{id}")
//	public void deleteVoteActivity(@PathParam("id") String id) {
//		VoteActivityService.deleteVoteActivity(Integer.valueOf(id)); 
//	}
//	
	@GET
	@Path("/students")
	@Produces({ "application/json", "application/xml" })
	public List<Student> getVoteActivities() {
		Student student=new Student();
		return student.queryAllStudnet();
	}
	
}
