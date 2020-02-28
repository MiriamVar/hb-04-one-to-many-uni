package hibernate.demo;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesDemo {

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

            //get the instructor from db
            int theID = 1;
            Instructor instructor = session.get(Instructor.class, theID);

            //create some courses
            Course course = new Course("Air guitar");
            Course course1 = new Course("Paintball");

            //add courses to instructor
            instructor.add(course);
            instructor.add(course1);

            //save the courses
            session.save(course);
            session.save(course1);

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
