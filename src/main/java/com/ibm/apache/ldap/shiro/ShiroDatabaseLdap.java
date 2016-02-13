package com.ibm.apache.ldap.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroDatabaseLdap {

    private static Logger logger = LogManager.getLogger(ShiroLDAPTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Using the IniSecurityManagerFactory, which will use the an INI file
        // as the security file.
        Factory<org.apache.shiro.mgt.SecurityManager> factory = 
            new IniSecurityManagerFactory("shiro.ini");

        // Setting up the SecurityManager...
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject user = SecurityUtils.getSubject();

        logger.info("User is authenticated:  " + user.isAuthenticated());

        UsernamePasswordToken token = 
        new UsernamePasswordToken(
            "cn=Cornelius Buckley,ou=people,o=sevenSeas", "argh");

        user.login(token);

        logger.info("User is authenticated:  " + user.isAuthenticated());
    }
}