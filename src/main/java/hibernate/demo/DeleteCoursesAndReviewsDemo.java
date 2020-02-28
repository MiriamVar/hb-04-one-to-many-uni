package hibernate.demo;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteCoursesAndReviewsDemo {

    public static void main(String[] args) {
        // Session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            //get the course
            int theId = 10;
            Course course = session.get(Course.class, theId);

            //print the source
            System.out.println("deleting");
            System.out.println(course);

            //print the course reviews
            System.out.println(course.getReviewList());

            //delete
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
