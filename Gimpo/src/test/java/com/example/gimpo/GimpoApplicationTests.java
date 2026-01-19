package com.example.gimpo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gimpo.notice.NoticeController;
import com.example.gimpo.notice.NoticeService;

@SpringBootTest
class GimpoApplicationTests {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private NoticeController noticeController;

	@Test
	void contextLoads() {
		/*
		// 테스트용 30개 데이터 삽입
		for(int i=1; i<=30; i++) {
			String title = String.format("테스트 데이터 : [%02d]", i);
			String content = String.format("테스트 데이터 : [%02d]-[%02d]\n[%02d]-[%02d]", i, i, i, i);
			this.noticeService.create(title, content);
		}
		*/
	}

}
