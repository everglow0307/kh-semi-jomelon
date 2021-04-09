package com.jomelon.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jomelon.dao.UserDAO;
import com.jomelon.dao.impl.UserDAOImpl;
import com.jomelon.domain.UserVO;
import com.jomelon.service.UserService;
import com.jomelon.util.MailAuth;

public class UserServiceImpl implements UserService {

	private UserDAO udao = new UserDAOImpl();

	// 회원가입
	@Override
	public int joinUser(HttpServletRequest req) {

		UserVO user = new UserVO();

		user.setUserId(req.getParameter("userId"));
		user.setUserPw(req.getParameter("userPw"));
		user.setUserEmail(req.getParameter("userEmail"));
		user.setUserName(req.getParameter("userName"));
		user.setUserGender(req.getParameter("userGender"));
		user.setUserPostCode(req.getParameter("userPostCode"));
		user.setUserRoadAddress(req.getParameter("userRoadAddress"));
		user.setUserDetailAddress(req.getParameter("userDetailAddress"));
		user.setUserPhone(req.getParameter("userPhone"));
		user.setUserBirth(req.getParameter("userBirth"));
		user.setUserPolicy(req.getParameter("userPolicy"));

		int result = udao.userInsert(user);

		return result;

	}

	//// 로그인페이지에서 입력한 정보 vo에 저장
	@Override
	public UserVO getLogin(HttpServletRequest req) {

		UserVO user = new UserVO();

		user.setUserId(req.getParameter("userId"));
		user.setUserPw(req.getParameter("userPw"));

		return user;
	}

	// 로그인 시도(로그인인증)
	@Override
	public int login(UserVO user) {
		return udao.selectUserCntByIdPw(user);
	}

	// 로그인 성공한 유저 정보 가져오기
	@Override
	public UserVO Info(UserVO user) {
		return udao.selectUserByUserId(user);
	}

	// 아이디 중복 체크
	@Override
	public int idCheck(String inputId) {

		System.out.println("service: " + inputId);

		int result = udao.userIdCheck(inputId);
		System.out.println(result);
		return result;
	}

	// 이메일 중복 체크
	@Override
	public UserVO emailCheck(String inputEmail) {
		return udao.userEmailCheck(inputEmail);
	}

	// 마이페이지조회 - 세션아이디, 입력비밀번호 저장
	public UserVO getMyInfoPw(HttpServletRequest req) {
		UserVO user = new UserVO();
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("id");

		user.setUserPw(req.getParameter("myInfoPw"));
		user.setUserId(user_id);
		return user;
	}

	// 세션에 저장된 아이디로 유저 정보가져오기 ??.?? -로그인성공한 유저 정보 가져오기.
	public UserVO getUserInfo(HttpServletRequest req) {
		UserVO user = new UserVO();

		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("id");
		user.setUserId(user_id);

		return udao.selectUserByUserId(user);

	}

	// 회원정보 수정
	public int userUpt(HttpServletRequest req) {

		UserVO user = new UserVO();

		user.setUserId(req.getParameter("userId"));
		user.setUserPw(req.getParameter("userPw"));
		user.setUserEmail(req.getParameter("userEmail"));
		user.setUserName(req.getParameter("userName"));
		user.setUserGender(req.getParameter("userGender"));
		user.setUserPostCode(req.getParameter("userPostCode"));
		user.setUserRoadAddress(req.getParameter("userRoadAddress"));
		user.setUserDetailAddress(req.getParameter("userDetailAddress"));
		user.setUserPhone(req.getParameter("userPhone"));
		user.setUserBirth(req.getParameter("userBirth"));

		int result = udao.userUpdate(user);

		return result;
	}

	// 회원탈퇴
	public int userDel(UserVO user) {
		int result = udao.userDelete(user);

		return result;
	}

	// 사용자 이메일로 인증번호 전송
	@Override
	public int[] sendUserMailAuthNo(String email) {

		int result[] = new int[] { 0, 0 };

		Properties prop = System.getProperties();

		prop.put("mail.smtp.starttls.enable", "true"); // 로그인시 TLS를 사용할 것인지 설정
		prop.put("mail.smtp.host", "smtp.gmail.com"); // 이메일 발송을 처리해줄 smtp(simple mail transfoer protocol)서버
		prop.put("mail.smtp.auth", "true"); // smtp서버의 인증을 사용한다는 의미
		prop.put("mail.smtp.port", "587"); // tls의 포트번호는 587, ssl의 포트번호는 465

		Authenticator auth = new MailAuth(); // MailAuth.java에서 Authenticator를 상속받아 session생성
		Session session = Session.getDefaultInstance(prop, auth);// 메일처리환경
		MimeMessage msg = new MimeMessage(session);
		// MimeMessage는 message클래스를 상속받은 인터넷 메일을 위한 클래스

		try {

			// 인증번호 생성
			result[0] = new Random().nextInt(10000) + 1001;
			System.out.println(result[0]);
			msg.setSentDate(new Date()); // 보내는 날짜 지정

			msg.setFrom(new InternetAddress("semiproject55@gmail.com", "jomelon"));// 발송자의 메일주소, 발송자명
			InternetAddress to = new InternetAddress(email); // 수신자 메일생성
			msg.setRecipient(Message.RecipientType.TO, to); // 수신자, 참조, 숨은 참조설정 가능
			msg.setSubject("Jomelon 비밀번호 찾기 인증번호입니다.", "UTF-8"); // 메일 제목
			msg.setText("인증번호는" + result[0] + "입니다.", "UTF-8"); // 메일 내용

			Transport.send(msg); // 메일 보내는 클래스와 메소드

			// 메일전송성공 결과값
			result[1] = 1;

			/*
			 * MessagingException 메일 계정인증 관련 예외 처리 UnsupportedEncodingException 지원되지 않는 인코딩을
			 * 사용할 경우 예외 처리
			 */

		} catch (AddressException ae) {
			System.out.println("AddressException : " + ae.getMessage());
		} catch (MessagingException me) {
			System.out.println("MessagingException : " + me.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException : " + e.getMessage());
		}

		return result;
	}

	// 비밀번호찾기용 - 비밀번호만 수정
	@Override
	public int userPasswordUpdate(HttpServletRequest req) {
		String userPw = req.getParameter("userPw");
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("u");
		String userEmail = user.getUserEmail();

		return udao.userPasswordUpdate(userPw, userEmail);
	}
}
