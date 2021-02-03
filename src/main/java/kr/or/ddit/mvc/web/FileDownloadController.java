package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("file")
@Controller
public class FileDownloadController {

	@Resource(name = "userService")
	private UserService userService;

	// localhost/file/fileDownloadview?userid=sally
	@RequestMapping("/fileDownloadView")
	public String fileDownloadView(String userid, Model model) {

		// 1. 다운로드 파일 경로 => realFilename
		// 2. 다운로드시 보여줄 파일명 ==> filename
		// 1,2 을 model에 넣어준다
		// userid 파라미터를 보낸다고 가정
		// 파라미터를 이용하여 해당 사용자의 사진 정보 (realFilname, filename)를 조회하여 처리

		UserVo userVo = userService.selectUser(userid);
		model.addAttribute("realFilename", userVo.getRealfilename());
		model.addAttribute("filename", userVo.getFilename());

		return "fd";
	}
	/*
	 * UPDATE users SET filename='test.jpg', realfilename='d:\\upload\test.jpg'
	 * WHERE userid = 'sally'; commit;
	 */

	// http://localhost:8081/file/fileDownload?userid=sally
	@RequestMapping("fileDownload")
	// 불가 public void fileDownload(OutputStream os, String userid) {
	public void fileDownload(HttpServletResponse response, String userid) {
		UserVo userVo = userService.selectUser(userid);

		// d:\\upload\\sally.png
		String realFilename = userVo.getRealfilename();
		String filename = userVo.getFilename();

		// 파일 다운로드 구현 header 사용
		response.setHeader("Content-Disposition", "attachement; filename=" + filename);

		ServletOutputStream sos;
		try {
			sos = response.getOutputStream();
			// ? 파일을 읽어온뒤 브라우저에 출력
			FileInputStream fis = new FileInputStream(new File(realFilename));
			byte[] buf = new byte[512];
			while (fis.read(buf) != -1) {
				sos.write(buf);
			}
			fis.close();
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
