const paginationNumbers = document.getElementById("pagination-numbers");
let totalElements = document.getElementById("total-elements").value;
let totalPages = document.getElementById("total-pages").value;
const nextButton = document.getElementById("next-button");
const prevButton = document.getElementById("prev-button");

const params = new URLSearchParams(location.search);
let paramPage = params.get('page');

const pathName = window.location.pathname;

const paginationLimit = 10;
const pageCount = totalPages;
let currentPage = (paramPage == null || paramPage <= 0) ? 1 : paramPage;

const disableButton = (button) => {
  button.classList.add("disabled");
  button.setAttribute("disabled", true);
};

const enableButton = (button) => {
  button.classList.remove("disabled");
  button.removeAttribute("disabled");
};

const handlePageButtonsStatus = () => {
  if (currentPage === 1) {
    disableButton(prevButton);
  } else {
    enableButton(prevButton);
  }

  if (pageCount == currentPage) {
    disableButton(nextButton);
  } else {
    enableButton(nextButton);
  }
};

const appendPageNumber = (index) => {
  const pageNumber = document.createElement("button");
  pageNumber.className = "pagination-number";
  pageNumber.innerHTML = index;
  pageNumber.setAttribute("page-index", index);
  pageNumber.setAttribute("aria-label", "Page " + index);

  paginationNumbers.appendChild(pageNumber);
};

const getPaginationNumbers = () => {

  let viewPageRangeStart = (parseInt(currentPage % paginationLimit) == 0 ? (parseInt(currentPage / paginationLimit) * 10) - 10 : parseInt(currentPage / paginationLimit) * 10) + 1;
  let viewPageRangeEnd = viewPageRangeStart + 9 < totalPages ? viewPageRangeStart + 9 : totalPages;
  for (let i = viewPageRangeStart; i <= viewPageRangeEnd; i++) {
    appendPageNumber(i);
  }
};

const handleActivePageNumber = () => {
  document.querySelectorAll(".pagination-number").forEach((button) => {
    button.classList.remove("active");
    const pageIndex = Number(button.getAttribute("page-index"));
    if (pageIndex == currentPage) {
      button.classList.add("active");
    }
  });
};

const setCurrentPage = (pageNum) => {
  currentPage = pageNum;

  handleActivePageNumber();
  handlePageButtonsStatus();
};

window.addEventListener("load", () => {
  getPaginationNumbers();
  setCurrentPage(currentPage);

  prevButton.addEventListener("click", () => {
    params.set('page', parseInt(currentPage) - 1);
    window.location.href = pathName + '?' + params;
  });

  nextButton.addEventListener("click", () => {
    params.set('page', parseInt(currentPage) + 1);
    window.location.href = pathName + '?' + params;
  });

  document.querySelectorAll(".pagination-number").forEach((button) => {
    const pageIndex = Number(button.getAttribute("page-index"));

    if (pageIndex) {
      button.addEventListener("click", () => {
        params.set('page', parseInt(pageIndex));
        window.location.href = pathName + '?' + params;
      });
    }
  });
});

function search() {
    let searchKeyword = document.getElementById("search").value;

    const searchParam = 'keyword=' + searchKeyword;
    window.location.href = pathName + '?' + searchParam;
}