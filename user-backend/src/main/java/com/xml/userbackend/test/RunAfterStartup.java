package main.java.com.xml.userbackend.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {
    @Autowired
    Test test;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
        System.out.println("LALALLLLLLLLALLLLL");
        test.testWriteToExistDb();
        test.testReadFromExistDb();
        test.testUpdateDocumentFromExistDb();
        test.testWriteToRdf();
        test.testReadFromRdf();
    }
}
