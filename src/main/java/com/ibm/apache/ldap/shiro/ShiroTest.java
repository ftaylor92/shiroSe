package com.ibm.apache.ldap.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroTest {

    private static Logger logger = LogManager.getLogger(ShiroTest.class);

    public static void main(String[] args) {
        // Using the IniSecurityManagerFactory, which will use the an INI file
        // as the security file.
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
        	//new JdbcRealm().getCacheManager().
            new IniSecurityManagerFactory("auth.ini");

        // Setting up the SecurityManager...
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject user = SecurityUtils.getSubject();

        logger.info("User is authenticated:  " + user.isAuthenticated());
        
        UsernamePasswordToken token = new UsernamePasswordToken("bjangles", "dance");
        
        user.login(token);
        
        logger.info("User is authenticated:  " + user.isAuthenticated());
    }
}