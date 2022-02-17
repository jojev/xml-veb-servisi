package main.java.com.xml.officialbackend.test;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.service.implementation.ListaCekanjaService;
import main.java.com.xml.officialbackend.service.implementation.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {
    @Autowired
    Test test;

    @Autowired 
    private ListaCekanjaService lcs;
    
    @Autowired
    TerminService ts;

    @Autowired
    ExistDbManager manager;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {

    	test.test();        
    }
}
