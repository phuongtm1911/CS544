package edu.mum.cs544;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class StudentDAO {

	public Student load(long studentid) {
		EntityManager em = EntityManagerHelper.getCurrent();
		EntityGraph<Student> graph = em.createEntityGraph(Student.class);
		graph.addAttributeNodes("courselist");

		TypedQuery<Student> query = em.createQuery("select s from Student s where s.studentid =: studentid", Student.class);
		query.setParameter("studentid", studentid);
		query.setHint("javax.persistence.fetchgraph", graph);
		Student student = query.getSingleResult();
		return student;
	}
}
