package hibernate.demo;

import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreatetDemo {

    public static void main(String[] args) {
        // Session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            // student object
            System.out.println("creating instructor");
            Instructor instructor = new Instructor("Mima", "Vargockova","mima@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("youtubeMim", "drawing");
            instructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            //saving student
            System.out.println("saving instructor");
            session.save(instructor);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        }
        finally {
            factory.close();
        }
    }
}
