package kr.co.jboard2.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.vo.ArticleVO;
import kr.co.jboard2.vo.FileVO;

public enum ArticleService {
	
	INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass()); 
	private ArticleDAO dao;
	
	private ArticleService() {
		dao = new ArticleDAO();
	}
	
	public int insertArticle(ArticleVO vo) {
		return dao.insertArticle(vo);
	}
	
	public void insertFile(int parent, String newName, String fname) {
		dao.insertFile(parent, newName, fname);
	}

	public int selectCountTotal () {
		return dao.selectCountTotal();
	}
	
	public ArticleVO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public FileVO selectFile(String parent) {
		return dao.selectFile(parent);
	}
	
	public List<ArticleVO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	
	public void updateArticle(String title, String content, String no) {
		dao.updateArticle(title, content, no);
	}
	
	public void updateFileDownload(int fno) {
		dao.updateFileDownload(fno);
	}
	
	public void updateArticleHit(String no) {
		dao.updateArticleHit(no);
	}
	
	// 추가적인 서비스 로직
	public MultipartRequest uploadFile(HttpServletRequest req, String path) throws IOException {
		int maxSize = 1024 * 1024 * 10;
		// MultipartRequest -> 파일 수신(스트림작업을 해줌) 
		return new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	}
	
	public String renameFile(String fname, String uid, String path) {
		// 파일명 수정
		int i = fname.lastIndexOf(".");
		String ext = fname.substring(i);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_");
		String now = sdf.format(new Date());
		// 20221026169417_본인 아이디.txt
		String newName = now+uid+ext; 
		
		File f1 = new File(path+"/"+fname);
		File f2 = new File(path+"/"+newName);
		
		f1.renameTo(f2);
		
		return newName;
	}
		
	public int getCurrentpage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		
		return currentPage;
	}
	
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = (total / 10);
		}else {
			lastPageNum = (total / 10) + 1;
		}
		
		if(total == 0) {
			lastPageNum = 1;
		}
		
		return lastPageNum;
	}
	
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		
		int pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
		int pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		
		return total - start;
	}
	
	public int getStartNum (int currentPage) {
		return (currentPage - 1) * 10;
	}
	
}
