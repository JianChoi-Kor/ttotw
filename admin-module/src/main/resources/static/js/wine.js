function handleOnChange(e) {
  const texts = [];

  // options 에서 selected 된 element 찾기
  for(let i=0; i < e.options.length; i++) {
    const option = e.options[i];
    if(option.selected) {
      texts.push(option.text);
    }
  }
  // 선택된 데이터 출력
  document.getElementById('texts').innerText = texts;
}


var fileInput = document.getElementById("wineImage");
fileInput.onchange = function() {
    var selectedFile = fileInput.files[0];
    // selectedFile 확장자 체크
    if (!(selectedFile.type == 'image/jpeg' || selectedFile.type == 'image/png')) {
        alert('허용된 확장자가 아닙니다.');
        fileInput.value = ''; // input value 초기화
        return;
    }

    readImageFile(selectedFile); // width, height 구하기 위한 메서드
};

function readImageFile(file) {
    var reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = function(e) {
        var img = new Image();
        img.src = e.target.result;

        img.onload = function() {
            var width = this.width;
            var height = this.height;
            if (width != 300 || height != 300) {
                 alert("300 x 300(px) 이미지만 업로드 가능합니다.");
                 fileInput.value = ''; // input value 초기화
                 return true;
            }
        }
    }
};

function registerSubmit() {
    let registerWineForm = document.getElementById('registerWine');

    if (registerWineForm.koreanName.value == '') {
        alert("제품명(한글)을 입력해주세요.");
        return;
    }
    if (registerWineForm.originName.value == '') {
        alert("제품명(영문)을 입력해주세요.");
        return;
    }
    if (registerWineForm.grade.value == '선택') {
        alert("등급을 선택해주세요.");
        return;
    }
    if (registerWineForm.type.value == '선택') {
        alert("타입을 선택해주세요.");
        return;
    }
    if (registerWineForm.price.value == '') {
        alert("가격을 입력해주세요.");
        return;
    }
    if (registerWineForm.country.value == '선택') {
        alert("생산지를 선택해주세요.");
        return;
    }
    if (registerWineForm.countryDetails.value == '') {
        alert('생산지 상세(영문)을 입력해주세요.');
        return;
    }
    if (registerWineForm.varieties.value == '') {
        alert('품종을 선택해주세요.');
        return;
    }
    if (registerWineForm.varietiesDetails.value == '') {
        alert('품종 상세(영문)을 입력해주세요.');
        return;
    }
    if (registerWineForm.wineImage.value == '') {
        alert('와인 이미지를 등록해주세요.');
        return;
    }

    const formData = new FormData(registerWineForm);
    fetch("http://localhost:8901/wine/register", {
        method: 'POST',
        headers: {},
        body: formData
    })
    .then((res) => {
        console.log(res);
        if(res.status === 200) {
            alert("등록에 성공했습니다.");
            location.href='/wine';
        } else if(res.status === 400) {
            alert("잘못된 요청값이 존재합니다.");
        } else {
            alert("요청 중 오류가 발생했습니다.");
        }
    }).catch(err => console.log("err: " + err));
}

function modifySubmit() {
    let registerWineForm = document.getElementById('registerWine');

    if (registerWineForm.koreanName.value == '') {
        alert("제품명(한글)을 입력해주세요.");
        return;
    }
    if (registerWineForm.originName.value == '') {
        alert("제품명(영문)을 입력해주세요.");
        return;
    }
    if (registerWineForm.grade.value == '선택') {
        alert("등급을 선택해주세요.");
        return;
    }
    if (registerWineForm.type.value == '선택') {
        alert("타입을 선택해주세요.");
        return;
    }
    if (registerWineForm.price.value == '') {
        alert("가격을 입력해주세요.");
        return;
    }
    if (registerWineForm.country.value == '선택') {
        alert("생산지를 선택해주세요.");
        return;
    }
    if (registerWineForm.countryDetails.value == '') {
        alert('생산지 상세(영문)을 입력해주세요.');
        return;
    }
    if (registerWineForm.varieties.value == '') {
        alert('품종을 선택해주세요.');
        return;
    }
    if (registerWineForm.varietiesDetails.value == '') {
        alert('품종 상세(영문)을 입력해주세요.');
        return;
    }
    if (registerWineForm.wineImage.value == '') {
        alert('와인 이미지를 등록해주세요.');
        return;
    }

    const formData = new FormData(registerWineForm);
    fetch("http://localhost:8901/wine/register", {
        method: 'POST',
        headers: {},
        body: formData
    });
}