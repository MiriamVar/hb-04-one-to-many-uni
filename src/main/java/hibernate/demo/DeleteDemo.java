package hibernate.demo;

import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteDemo {

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

            //start a transaction
            session.beginTransaction();

            int id =4;
            Instructor swapInstructor = session.get(Instructor.class,id);

            //deleting student
            if (swapInstructor != null){
                System.out.println("deleting");
                session.delete(swapInstructor);
            }

            //commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        }
        finally {
            factory.close();
        }
    }
}
