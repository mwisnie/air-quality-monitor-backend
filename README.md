# air-quality-monitor-backend

<h3>Summary:</h3>
<p>
  Backend of an app that regularely checks public air quality data and can graph it and send warnings to registered users in case   of bad air quality.
  <br><br>
  Users will be able to choose up to 5 measurement stations. Scheduled periodic task will read air quality data from those stations, and if theh data exceeds certain level of contamination, email alert can be sent.
  <br><br>
  Air quality data will be provided by public API: http://powietrze.gios.gov.pl/pjp/content/api
  <br><br>
  Front will be made in Angular.
</p>

<br>

<h3>Stack:</h3>
<ul>
  <b>Backend:</b>
  <br><br>
  
  <li>Spring Boot</li>
  <li>Spring Data</li>
  <li>Spring Security + JWT authentication</li>
  <li>MongoDB</li>
  <li>REST API</li>
  <li>Spock</li>
  
  <br>
  <b>Frontend:</b>
  <br><br>
  
  <li>Angular 6</li>
  <li>Bootstrap 3/4</li>
  
</ul>

<br>

<h3>Relase plans:</h3>
<p>
  1: air quality data processing and email alerts in case of bad quality
  <br>
  2: storage, processing and plotting of air quality data
  <br><br>
  App will be deployed on Amazon EC2
</p>
