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