package net.oebs.namo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import net.oebs.namo.core.PdnsRecordDAO;
import net.oebs.namo.core.Subdomain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerDNSSyncThread extends Thread implements BackendSyncInterface {

    static final Logger log = LoggerFactory.getLogger(PowerDNSSyncThread.class);
    private final BlockingQueue queue;
    private PdnsRecordDAO recordDAO;
    private final SessionFactory sessionFactory;

    public PowerDNSSyncThread(SessionFactory factory) {
        this.queue = new ArrayBlockingQueue(128);
        this.recordDAO = new PdnsRecordDAO(factory);
        this.sessionFactory = factory;
    }

    @Override
    public void run() {
        log.info("PowerDNSSyncThread running");
        while (true) {
            loop();
        }
    }

    private void loop() {
        Subdomain sub;
        try {
            // Call take() because we want to block until something is available
            sub = (Subdomain) queue.take();
        } catch (InterruptedException e) {
            log.error("queue.take() failed with {}", e);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
            }
            return;
        }

        log.info("powerdns sync wake up with: ", sub.getSubdomainId());

        /* Becase this standalone thread is not a jersey resource the
         * @UnitOfWork annotation does not work and we have to manually take
         * care of setting up a hibernate session.
         */
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        sync(sub);
        session.close();
    }

    private void sync(Subdomain sub) {
        recordDAO.create(sub);
    }

    @Override
    public void wakeup(Object o) {
        /* Call the non-blocking offer() because this call is likely part of a web
         * request and we don't want to block while serving the request.
         */
        boolean success = queue.offer(o);
        log.info("wakeup queue offer: {} {}", success, o);
    }
}
