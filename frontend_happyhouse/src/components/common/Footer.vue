<template>
  <div>
    <!-- Font Awesome -->
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"
    />
    <!-- Google Fonts Roboto -->
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"
    />

    <!-- Footer -->
    <footer class="text-lg-start text-white" style="background-color: #1e90ff">
      <!-- Grid container -->
      <div class="container">
        <!-- Section: Links -->
        <section class="">
          <!--Grid row-->
          <div class="row" align="center">
            <!-- Grid column -->
            <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
              <h6 class="text-uppercase mb-4 font-weight-bold">
                보금 자리 찾기
              </h6>
              <p align="center">
                내가 꿈꾸던 집!<br />
                여기서 찾아가세요!
              </p>
            </div>
            <!-- Grid column -->

            <hr class="w-100 clearfix d-md-none" />

            <!-- Grid column -->
            <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
              <h6 class="text-uppercase mb-4 font-weight-bold">Menu</h6>
              <p>
                <a class="text-white" href="/house">부동산</a>
              </p>
              <p>
                <a class="text-white" href="/notice">자유게시판</a>
              </p>
              <p>
                <a class="text-white" href="/news">뉴스</a>
              </p>
            </div>
            <!-- Grid column -->

            <hr class="w-100 clearfix d-md-none" />

            <!-- Grid column -->
            <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mt-3">
              <h6 class="text-uppercase mb-4 font-weight-bold">Useful links</h6>
              <p>
                <a class="text-white" href="http://rt.molit.go.kr/"
                  >국토교통부</a
                >
              </p>
              <p>
                <a
                  class="text-white"
                  href="https://new.land.naver.com/complexes?ms=35.1922446,126.81947,15&a=APT:JGC:ABYG&e=RETAIL&ad=true"
                  >네이버 부동산</a
                >
              </p>
              <p>
                <a
                  class="text-white"
                  href="https://www.bdsplanet.com/map/realprice_map.ytp"
                  >부동산 플래닛</a
                >
              </p>
            </div>

            <!-- Grid column -->
            <hr class="w-100 clearfix d-md-none" />

            <!-- Grid column -->
            <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
              <h6 class="text-uppercase mb-4 font-weight-bold">Contact</h6>
              <p>
                <i class="fas fa-home mr-3"></i> 광주광역시 광산구 하남산단
                6번로 107
              </p>
              <p><i class="fas fa-envelope mr-3"></i> ssafy@ssafy.com</p>
              <p><i class="fas fa-phone mr-3"></i> 062-0000-0000</p>
            </div>
            <!-- Grid column -->
          </div>
          <!--Grid row-->
        </section>
        <!-- Section: Links -->

        <hr class="my-3" />

        <!-- Section: Copyright -->
        <section class="p-3 pt-0">
          <div class="row d-flex align-items-center">
            <!-- Grid column -->
            <div class="col-md-7 col-lg-8 text-center text-md-start">
              <!-- Copyright -->
              <div class="p-3">
                © 2021 Copyright: 서은민, 박주한<br />
                <a class="text-white" href="https://www.ssafy.com/"
                  >www.ssafy.com</a
                >
              </div>
              <!-- Copyright -->
            </div>
            <!-- Grid column -->

            <!-- Grid column -->
            <div class="col-md-5 col-lg-4 ml-lg-0 text-center text-md-end">
              <!-- Github (은민) -->
              <a
                class="btn btn-outline-light btn-floating m-1 text-white"
                role="button"
                href="https://github.com/Mongerr"
                ><i class="fab fa-github"></i
              ></a>
              <!-- Github (주한) -->
              <a
                class="btn btn-outline-light btn-floating m-1 text-white"
                role="button"
                href="https://github.com/parkjoohan"
                ><i class="fab fa-github"></i
              ></a>
            </div>
            <!-- Grid column -->
          </div>
        </section>
        <!-- Section: Copyright -->
      </div>
      <!-- Grid container -->
    </footer>
    <!-- Footer -->
  </div>
  <!-- End of .container -->
</template>

<script>
export default {
  name: "FooterNav",
};
</script>

<style>
footer {
  font-family: "Nunito", sans-serif;
}
</style>
