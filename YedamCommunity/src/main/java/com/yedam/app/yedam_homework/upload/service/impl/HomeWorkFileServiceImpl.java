package com.yedam.app.yedam_homework.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;
import com.yedam.app.yedam_homework.upload.mapper.HomeWorkFileMapper;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileService;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileVO;
import com.yedam.app.yedam_homework.upload.service.ReplyFileVO;

@Service
public class HomeWorkFileServiceImpl implements HomeWorkFileService {

	@Value("${file.upload.path}")
	private String uploadPath;

	@Autowired
	HomeWorkFileMapper homeworkfileMapper;

	// 과제등록 파일 업로드
	@Override
	public List<String> homeworkUploadFile(@RequestPart MultipartFile[] uploadFiles,
			HomeWorkTargetVO homeworktargetVO) {

		List<String> FileList = new ArrayList<>();

		System.err.println(uploadFiles.length);

		for (MultipartFile uploadFile : uploadFiles) {

			System.err.println("uploadFiles =" + uploadFiles);

			// 모든경로를 포함한 파일 이름
			String originalName = uploadFile.getOriginalFilename();
			System.err.println("오리지날 이름 = " + originalName);

			// 모든 경로에서 마지막 / 부분으로부터 +1 해준 부분부터 출력하겠다.
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
			System.err.println("파일 이름 = " + fileName);

			// 확장자
			String fileExt = originalName.substring(originalName.lastIndexOf(".") + 1);
			System.err.println(fileExt);

			// 날짜 폴더 생성
			String folderPath = makeFolder();
			System.err.println("날짜 폴더 생성 = " + folderPath);

			// UUID = 시간을 기준으로 랜덤한 값
			String uuid = UUID.randomUUID().toString();
			System.err.println("uuid = " + uuid);

			// 저장할 파일이름 중간에 "_"를 이용하여 구분
			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;
			System.err.println("파일 이름 중간에 _ 구분 = " + uploadFileName);

			// 다운로드 파일 경로
			String downloadFileName = uuid + "_" + fileName;

			// 저장할때 이름 = 경로 + / + 랜덤한 파일 이름
			String saveName = uploadPath + File.separator + uploadFileName;
			System.err.println("저장할 때 이름 = " + saveName);

			// Paths.get() 매서드는 특정 경로의 파일 정보를 가져옴(경로 정의)
			Path savePath = Paths.get(saveName);

			try {
				uploadFile.transferTo(savePath); // uploadFile에 파일을 업로드하는 매서드 transgerTo(file)
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.err.println("파일 사이즈" + uploadFile.getSize());

			FileList.add(setFilepath(uploadFileName));
			System.err.println("FileList = " + FileList);

			HomeWorkFileVO homeworkfileVO = new HomeWorkFileVO();
			// 파일이름
			homeworkfileVO.setHomeworkfileName(fileName);
			// 파일 경로
			homeworkfileVO.setHomeworkfileLocation(saveName);
			// 파일 사이즈
			homeworkfileVO.setHomeworkfileSize((int) uploadFile.getSize());
			// 파일 확장자
			homeworkfileVO.setHomeworkfileExt(fileExt);
			// 파일 다운로드 경로
			homeworkfileVO.setDownloadLocation(downloadFileName);
			// 파일 과제번호
			homeworkfileVO.setHomeworkId(homeworktargetVO.getHomeworkId());

			if (!fileName.equals("") && !fileName.equals(null)) {
				homeworkfileMapper.insertHomeWorkFile(homeworkfileVO);
			}
		}

		return FileList;
	}

	@Override
	public List<String> replyUploadFile(MultipartFile[] uploadFiles, ReplyVO reply) {

		List<String> FileList = new ArrayList<>();

		for (MultipartFile uploadFile : uploadFiles) {

			System.err.println("uploadFiles =" + uploadFiles);

			// 모든경로를 포함한 파일 이름
			String originalName = uploadFile.getOriginalFilename();

			// 모든 경로에서 마지막 / 부분으로부터 +1 해준 부분부터 출력하겠다.
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);

			// 확장자
			String fileExt = originalName.substring(originalName.lastIndexOf(".") + 1);

			// 날짜 폴더 생성
			String folderPath = makeFolder();

			// UUID = 시간을 기준으로 랜덤한 값
			String uuid = UUID.randomUUID().toString();

			// 저장할 파일이름 중간에 "_"를 이용하여 구분
			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;

			// 다운로드 파일 경로
			String downloadFileName = uuid + "_" + fileName;

			// 저장할때 이름 = 경로 + / + 랜덤한 파일 이름
			String saveName = uploadPath + File.separator + uploadFileName;

			// Paths.get() 매서드는 특정 경로의 파일 정보를 가져옴(경로 정의)
			Path savePath = Paths.get(saveName);

			try {
				uploadFile.transferTo(savePath); // uploadFile에 파일을 업로드하는 매서드 transgerTo(file)
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.err.println("파일 사이즈" + uploadFile.getSize());

			FileList.add(setFilepath(uploadFileName));

			ReplyFileVO replyfileVO = new ReplyFileVO();
			// 파일이름
			replyfileVO.setReplyfileName(fileName);
			// 파일 경로
			replyfileVO.setReplyfileLocation(saveName);
			// 파일 사이즈
			replyfileVO.setReplyfileSize((int) uploadFile.getSize());
			// 파일 확장자
			replyfileVO.setReplyfileExt(fileExt);
			// 파일 다운로드 경로
			replyfileVO.setDownloadLocation(downloadFileName);
			// 파일 과제번호
			replyfileVO.setReplyId(reply.getReplyId());
			if (!fileName.equals("") && !fileName.equals(null)) {
				homeworkfileMapper.insertReplyfFile(replyfileVO);
			}

		}

		return FileList;
	}

	private String makeFolder() {

		// LocalDate를 문자열로 포맷
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		// / str.replace = str에 "/"를 File.separator로 바꾼다.
		String folderPath = str.replace("/", File.separator);

		// uploadPath경로에 folderpath이름의 폴더 생성
		// File newFile = new File (dir,"파일명"); -> 부모 디렉토리를 파라미터로 인스턴스 생성
		File uploadPathFolder = new File(uploadPath, folderPath);

		if (uploadPathFolder.exists() == false) { // 만약 uploadPathFolder가 존재하지 않는다면 makeDirectory하라는 의미
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
			uploadPathFolder.mkdirs();
		}
		return folderPath;
	}

	private String setFilepath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}

	// 과제파일 조회
	@Override
	public List<HomeWorkFileVO> homeworkfileList(HomeWorkVO homeworkVO) {
		return homeworkfileMapper.selectHomeworkfile(homeworkVO);
	}
	
	//댓글 파일 조회
	@Override
	public List<ReplyFileVO> replyfileList(ReplyVO reply) {
		return homeworkfileMapper.selectReplyfile(reply);
	}

}
