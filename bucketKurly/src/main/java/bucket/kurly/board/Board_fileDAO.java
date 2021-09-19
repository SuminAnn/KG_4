package bucket.kurly.board;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Board_fileDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public void insertBoard_qnaFile(Board_fileVO vo) {
		 System.out.println("Board_fileDAO - insertBoard_qnaFile() 실행");
		 sqlSessionTemplate.insert("board-mapping.insertBoard_qnaFile",vo); 
	}

}

