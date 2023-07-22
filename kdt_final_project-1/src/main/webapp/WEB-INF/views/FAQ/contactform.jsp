<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  <link rel="stylesheet" type = "text/css" href="css/FAQ/main.css">
<!--   <link rel="stylesheet" type = "text/css" href="css/FAQ/reset.css"> -->
</head>
<body>
<!-- header -->
<%@ include file="../home/header.jsp"%>

  <main>
	  <div class="section-container">
		  
    <div class="left-section">
      <!-- 왼쪽 섹션 -->
      <h2>문의 분류</h2>
      <br>
<ul class="category-list">
  <li><a href="/FAQ01">일반</a></li>
  <li><a href="/FAQ02">가입 변경/탈퇴</a></li>
  <li><a href="/FAQ03">신고/이용제한</a></li>
  <li><a href="/FAQ04">프로필 관련</a></li>
    <li><a href="FAQ">이용 문의</a></li>
</ul>
    </div>
    
    <div class="right-section">
      <!-- 오른쪽 섹션 -->
      <h2>문의글 작성 양식</h2>
      <br>
      
      <form action="/FAQ/submit" method="post" enctype="multipart/form-data">
		  <div class = "form-group">
        <label for="email">이메일 주소</label>
       <input type="email" id="email" name="email" class="form-control" placeholder="이메일 주소를 입력하세요" required>
		  </div>
            		  

        <label  for="phone">휴대폰 번호</label>
		  <div class = "form-group-phone">
        <select class = "wrap_select_nation" id="country-code">
          <!-- 국가번호 옵션 추가 -->
            <option value="+1">+1 (USA)</option>
  <option value="+44">+44 (UK)</option>
  <option value="+82">+82 (South Korea)</option>
  
        </select>

        <div class= "wrap_phone"> 
        <input type="text" class = "form-control-phone" id="phone" name="phone" placeholder="전화번호를 입력하세요" required>
        </div>
      </div>
        
        <div class = "form-group">
        <label for="categories">문의 분류</label>
        <select id="categories" class = "form-control-category" name="categories">
             <option value="일반">일반</option>
  <option value="가입 변경/탈퇴">가입 변경/탈퇴</option>
  <option value="신고/이용제한">신고/이용제한</option>
  <option value="프로필 관련">프로필 관련</option>
  <option value="기타">기타</option>
        </select>
        </div>
        
        <div class = "form-group">
        <label for="title">제목</label>
        <textarea class = "form-control" id="title" name="title" placeholder="제목을 입력하세요" required></textarea>
        </div>
        
        <div class = "form-group">
        <label for="title">문의 내용</label>
        <textarea class = "form-control-textarea" id="content-textarea" name="content" required></textarea>
        </div>
        
        <div>파일 첨부</div>
       <label for="attachment-input" class="custom-file-button" id="file-name-display">첨부할 파일을 선택하세요
  <input type="file" class="form-control-attachment" id="attachment-input" name="imageFileName" accept='image/*'>
</label>
<!-- <span id="file-cancel-button" class="file-cancel-button" style="display: none;">파일 선택 취소</span>
 -->
        
  
        <div class="privacy-policy">
			<p class="bold-text">개인정보 수집·이용에 대한 안내</p><br>
<h5>(필수) 개인정보 수집·이용에 대한 안내</h5><br>
<h5>(주)위트는 이용자 문의를 처리하기 위해 다음과 같이 개인정보를 수집 및 이용하며, 이용자의 개인정보를 안전하게 취급하는데 최선을 다하고 있습니다.</h5>
<br>
<table class="table-control">
  <tr>
    <th>수집항목</th>
    <th>수집목적</th>
    <th>보유기간</th>
  </tr>
  <tr>
    <td>이메일 주소, 휴대폰 번호</td>
    <td>문의・요청・불편사항 확인 및 <br>처리결과 회신</td>
    <td>3년간 보관 후 지체없이 파기</td>
  </tr>
</table>

<br>
<h5>위 동의를 거부할 권리가 있으며, 동의를 거부하실 경우 문의 처리 및 결과 회신이 제한됩니다.</h5><br>

<h5>더 자세한 내용에 대해서는 위트 개인정보처리방침을 참고하시기 바랍니다.</h5>
<br>      
    <div class="checkbox-container">
  <input type="checkbox" id="agree-checkbox" name="agree_yn" required>
  <label for="agree-checkbox">위 내용에 동의합니다.</label>
</div>
        </div>
<br>        
        <button type="submit">문의접수</button>
      </form>
    </section>
    </div>
    
    <script>
  const countryCodeSelect = document.getElementById("country-code");
  const phoneNumberInput = document.getElementById("phone");
  
  countryCodeSelect.addEventListener("change", function() {
    const selectedCountryCode = countryCodeSelect.value;
    phoneNumberInput.value = selectedCountryCode;
  });
</script>
    
<script>
const fileInput = document.getElementById("attachment-input");
const fileNameDisplay = document.getElementById("file-name-display");

fileInput.addEventListener("change", function() {
  const fileName = fileInput.files[0].name;
  fileNameDisplay.textContent = fileName;
});

</script>










    
  </main>

<!-- top button -->
<%@ include file="../home/topbutton.jsp"%>

<!-- footer -->
<%@ include file="../home/footer.jsp"%>



</body>
</html>