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

function submit() {
    let registerWineForm = document.getElementById('registerWine');
    const formData = new FormData(registerWineForm);
    fetch("http://localhost:8901/wine/register", {
        method: 'POST',
        headers: {},
        body: formData
    });
}