package hibernate.demo;

import demo.entity.Instructor;
import demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteInstructorDetailDemo {

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

            int id = 1;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);
             System.out.println("instructor "+ tempInstructorDetail.getInstructor());

            //deleting
            System.out.println("deleting instructor detail");
            //break bi-directional reference
            tempInstructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(tempInstructorDetail);
            //commit transaction
            session.getTransaction().commit();

            System.out.println("DONE!");
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close(); //connection leak 

            factory.close();
        }
    }
}
