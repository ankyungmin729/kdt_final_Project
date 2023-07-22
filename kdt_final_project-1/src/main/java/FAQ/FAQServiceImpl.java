package FAQ;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FAQ.FAQDAO;

@Service
@Transactional
public class FAQServiceImpl implements FAQService {

    @Autowired
    private FAQDAO faqDAO;

    @Override
    public void createArticle(FAQDTO faqDTO) {
        faqDAO.insertArticle(faqDTO);
    }


}
