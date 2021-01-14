<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Weather Analysis</title>
<link rel="stylesheet" href="stylingSheet.css">
<style>
table {
  border-collapse: collapse;
  width: 30%;
}
th, td {
  text-align: left;
  padding: 8px;
}
tr:nth-child(even) {background-color: #f2f2f2;}
h1 {
   text-align:center;
}
body{
	background-color: #FFFAFA;
}
.button{
  background-color: #FF6347;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.current{
  position: absolute;
  left: 0;
  top: 8%;
  width: 100%;
  text-align: center;
  font-size: 18px;
}
.info{
  position: absolute;
  top: 15%;
  width: 100%;
  font-size: 24px;
  text-align: center;
}

/* Image carousal styling */
.slide {display: none}
.carousal{
  max-width: 400px;
  position: absolute;
  margin: auto;
  top: 9px;
  right: 20px;
}
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  padding: 16px;
  margin-top: -22px;
  color: white;
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  border-radius: 0 3px 3px 0;
  user-select: none;
}
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}
.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
}
#curve_chart{
	margin: auto;
	margin-left: auto;
    margin-right: auto;
    padding-top: 10px;
}
</style>
<!-- Google curve chart-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Months', 'Low Temperature', 'High Temperature'],
          ['Jan',  45,      65],
          ['Feb',  34,      67],
          ['Mar',  25,      50],
          ['Apr',  45,      68],
          ['May',  67,      79],
          ['Jun',  70,      80],
          ['Jul',  75,      85],
          ['Aug',  76,      90],
          ['Sep',  70,      80],
          ['Oct',  65,      75],
          ['Nov',  60,      70],
          ['Dec',  45,      65],
        ]);

        var options = {
          title: 'Average Highs And Lows Of Previous Year',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>

</head>
<html>
<body>

<h1>Weather Analysis</h1>
<!-- Favorite cities table-->
<div style="overflow-y:auto;">
  <table>
  <tr>
   <th>Favorite Cities</th>
   </tr>
   <tr>
   <td>Boston, MA</td>
   </tr>
   <td>Los Angeles, CA</td>
   <tr>
   <td>New York City, NY</td>
   </tr>
   <tr>
   <td>San Francisco, CA</td>
   <tr>
   <td>Seattle, WA</td>
   </tr>
  </table>
   <button>
      <input type="button" class="button" value="Remove From Favorites">
   </button>
</div>
<div>
<!-- Current weather data -->
<div class = "current">
	<h3>Los Angeles, California</h3>
	<img src ="cloudy-day-1.svg" height = 250 width = 250></img>
</div>
<div class = "info">
	<h4>57F</h4>
	<h4>Cloudy</h4>
</div>
<!-- 5 day forecast table-->
<table align="center">
    <tr>
      <th>1/31</th>
      <th>2/1</th>
      <th>2/2</th>
      <th>2/3</th>
      <th>2/4</th>
    </tr>
    <tr>
      <th><img src ="cloudy-day-1.svg"></img></th>
      <th><img src ="cloudy-day-3.svg"></th>
      <th><img src ="cloudy-day-2.svg"></th>
      <th><img src ="cloudy-day-1.svg"></th>
      <th><img src ="cloudy-day-3.svg"></th>
    </tr>
    <tr>
      <th>Day 1</th>
      <th>Day 2</th>
      <th>Day 3</th>
      <th>Day 4</th>
      <th>Day 5</th>
    </tr>
    <tr>
    </tr>
    <tr>
      <th>H:65F</th>
      <th>H:73F</th>
      <th>H:68F</th>
      <th>H:65F</th>
      <th>H:75F</th>
    </tr>
    <tr>
      <th>L:38F</th>
      <th>L:42F</th>
      <th>L:39F</th>
      <th>L:30F</th>
      <th>L:45F</th>
    </tr>
</table>
</div>

<div class="carousal">
  <h3>January 31, 2020</h3>
  <div class="slide">
    <img src="newyork.jpeg" style="width:100%">
  </div>
  <div class="slide">
    <img src="hey.jpg" style="width:100%">
  </div>
  <div class="slide">
    <img src="tokyo.jpg" style="width:100%">
  </div>
  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
  <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<div id="curve_chart" style="width: 400px; height: 200px"></div>

</body>
</html>
<script>
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}
function showSlides(n) {
	  var i;
	  var slides = document.getElementsByClassName("slide");
	  if (n > slides.length) {slideIndex = 1}    
	  if (n < 1) {slideIndex = slides.length}
	  for (i = 0; i < slides.length; i++) {
	      slides[i].style.display = "none";  
	  }
	  slides[slideIndex-1].style.display = "block";  
	}
</script>