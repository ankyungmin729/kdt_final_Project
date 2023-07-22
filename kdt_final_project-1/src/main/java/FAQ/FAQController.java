package FAQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;




@Controller
/* @RequestMapping("/FAQ") */

public class FAQController {
	
	@RequestMapping("/FAQ")
	public String FAQ() {

		return "/FAQ/contactform";
		/* 아무거나 */
	}

	@RequestMapping("/FAQ01")

	public String FAQ01() {

		return "/FAQ/contactform_FAQ01";
		
	}
	@RequestMapping("/FAQ02")

	public String FAQ02() {

		return "/FAQ/contactform_FAQ02";
		
	}
	@RequestMapping("/FAQ03")

	public String FAQ03() {

		return "/FAQ/contactform_FAQ03";
		
	}

	@RequestMapping("/FAQ04")

	public String FAQ04() {

		return "/FAQ/contactform_FAQ04";
		
	}
	
	@RequestMapping("/FAQBoard")
	public String FAQBoard() {

		return "/FAQ/contactBoard";
	}
	
	private final FAQDAO faqDAO;

    @Autowired
    public FAQController(FAQDAO faqDAO) {
        this.faqDAO = faqDAO;
    }

    @PostMapping("/FAQ/submit")
    public String submitContactForm(HttpServletRequest request,@RequestParam("imageFileName") MultipartFile imageFile) {
    	String title = request.getParameter("title");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String categories = request.getParameter("categories");
        String content = request.getParameter("content");
        String agree_yn = request.getParameter("agree_yn");
        String imageFileName = imageFile.getOriginalFilename();

        
        
        // FaqDTO 객체 생성 및 필드 설정
        FAQDTO faqDTO = new FAQDTO();
        faqDTO.setTitle(title);
        faqDTO.setEmail(email);
        faqDTO.setPhone(phone);
        faqDTO.setCategories(categories);
        faqDTO.setContent(content);
        faqDTO.setAgree_yn(agree_yn);
        faqDTO.setImageFileName(imageFileName);
        faqDTO.setImageFile(imageFile);
        
        // faqDAO를 통해 데이터 저장
        faqDAO.insertArticle(faqDTO);

        return "redirect:/FAQ/success";
    }

	
}