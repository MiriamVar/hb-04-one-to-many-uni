package hibernate.demo;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesAndReviewsDemo {

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

            //create a course
            Course course = new Course("Pacman - how to score one milion points");

            //add some reviews
            course.add(new Review("Greate course ... loved it"));
            course.add(new Review("Cool course, job well done"));
            course.add(new Review("What a dump course, you are an idiot"));

            //save the course .. and leverage the cascade all
            System.out.println("Saving the course");
            System.out.println(course);
            System.out.println(course.getReviewList());

            session.save(course);

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
