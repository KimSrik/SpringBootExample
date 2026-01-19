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
		for(int i=1; i<=30; i++) {
			String title = String.format("테스트 데이터 : [%02d]", i);
			String content = "내용없음";
			this.noticeService.create(title, content);
		}
		*/
	}

}
