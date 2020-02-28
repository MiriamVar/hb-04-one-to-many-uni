package hibernate.demo;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteCoursesDemo {

    public static void main(String[] args) {
        // Session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            //get a course
            int theID  = 10;
            Course course = session.get(Course.class, theID);

            //delete course
            System.out.println("Deleting course: "+course);

            session.delete(course);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
