package FAQ;

import org.springframework.web.multipart.MultipartFile;

public class FAQDTO {
    private int articleNO;
    private String title;
    private String content;
    private String categories;
    private String email;
    private String phone;
    private String agree_yn;
    private String imageFileName;
    private MultipartFile imageFile;

    // Getter와 Setter 메서드 추가

    public String getAgree_yn() {
		return agree_yn;
	}

	public void setAgree_yn(String agree_yn) {
		this.agree_yn = agree_yn;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImageFileName() {
	    return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
	    this.imageFileName = imageFileName;
	}

	public MultipartFile getImageFile() {
	    return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
	    this.imageFile = imageFile;
	}
	
	@Override
    public String toString() {
        return "FAQDTO [articleNO=" + articleNO + ", title=" + title + ", content=" + content +
                ", categories=" + categories + ", email=" + email + ", phone=" + phone +
                ", imageFileName=" + imageFileName + ",imageFile=\" + imageFile + \", agree_yn=\" + agree_yn + \"]";
    }
}
