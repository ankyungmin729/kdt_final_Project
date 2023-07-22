package FAQ;

import FAQ.FAQDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FAQDAOImpl implements FAQDAO {
    private final SqlSession sqlSession;

    @Autowired
    public FAQDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insertArticle(FAQDTO faqDTO) {
        sqlSession.insert("FAQ.FAQDAO.insertArticle", faqDTO);
    }
}
