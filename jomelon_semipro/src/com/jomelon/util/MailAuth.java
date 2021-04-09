package com.jomelon.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
	PasswordAuthentication pa;
	
	public MailAuth() {
		String mailId="semiproject55@gmail.com";
		String mailPw="jomelon1!";
		
		pa = new PasswordAuthentication(mailId,mailPw);
		
		}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}
