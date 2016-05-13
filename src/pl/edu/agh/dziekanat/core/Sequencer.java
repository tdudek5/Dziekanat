package pl.edu.agh.dziekanat.core;

import java.io.Serializable;
//import java.util.Iterator;
//import java.util.List;

import org.hibernate.HibernateException;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import pl.edu.agh.dziekanat.persistance.BusinessSessionFactory;

public class Sequencer implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {

        BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
        Session session = bsf.getSession().openSession();

        int moduleID = 0;

        if (arg1 instanceof Module) {
            moduleID = ((Module) arg1).getModuleID();
        }

        Sequence sequence = (Sequence) session.load(Sequence.class, moduleID);

        sequence.setLastID(sequence.getLastID() + 1);
        session.beginTransaction();
        session.save(sequence);
        session.getTransaction().commit();

        String formattedModuleID = String.format("%03d", moduleID);

        int oid = Integer.valueOf(sequence.getLastID() + formattedModuleID);

        return oid;
    }

}
